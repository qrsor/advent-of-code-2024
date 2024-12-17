package pl.qrsor.adventofcode2024.day6;

import pl.qrsor.adventofcode2024.CharMatrixInput;
import pl.qrsor.adventofcode2024.Dimensions;
import pl.qrsor.adventofcode2024.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Day6Problem2 {

    public static void main(String[] args) {
        System.out.println("Result " + new Day6Problem2().solve("day6-problem1-input"));
    }

    public static <T, R> List<R> processListConcurrently(Set<T> items, ItemProcessor<T, R> processor) {
        // Create a list to store results
        List<R> results = new ArrayList<>();

        // Use virtual thread executor
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            // Submit a task for each item
            List<Future<R>> futures = new ArrayList<>();
            for (T item : items) {
                Future<R> future = executor.submit(() -> {
                    // Process individual item with access to the item
                    return processor.process(item);
                });
                futures.add(future);
            }

            // Collect results
            for (Future<R> future : futures) {
                results.add(future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    int countDistinctVisitedPositions(char[][] map) {
        var guard = new LoopingGuard(map);

        var journeyContinues = true;
        while (journeyContinues) {
            try {
                guard.walk();
            } catch (CannotWalkException | WalkingInCirclesException e) {
                journeyContinues = false;
            }
        }

        var start = guard.startedAt();
        Set<Position> obstacles = guard.obstacles();
        Dimensions mapDimensions = guard.mapDimensions();

        Set<Position> positions = guard.traceFootsteps()
                .stream().map(Footstep::position)
                .filter(Predicate.not(start::equals))
                .collect(Collectors.toSet());

        List<Integer> results = processListConcurrently(positions, item -> {
            // Simulate some processing
            var minion = new LoopingGuard(obstacles, item, start, mapDimensions);

            boolean walks = true;
            int result = 0;
            while (walks) {
                try {
                    minion.walk();
                } catch (CannotWalkException e) {
                    walks = false;
                } catch (WalkingInCirclesException e) {
                    walks = false;
                    result = 1;
                }
            }

            return result;
        });


        return results.stream().mapToInt(i -> i).sum();
    }

    int solve(String filePath) {
        return countDistinctVisitedPositions(CharMatrixInput.readInput(filePath));
    }

    @FunctionalInterface
    public interface ItemProcessor<T, R> {
        R process(T item) throws Exception;
    }
}
