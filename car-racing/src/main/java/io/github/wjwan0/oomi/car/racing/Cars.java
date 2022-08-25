package io.github.wjwan0.oomi.car.racing;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class Cars {

    private final List<Car> carList;

    private Cars(List<Car> carList) {
        this.carList = carList;
    }

    public static Cars from(String inputs) {
        return new Cars(
                Arrays.stream(inputs.split(","))
                        .map(String::trim)
                        .map(name -> new Car(name, new MoveImpl()))
                        .collect(toList())
        );
    }

    public List<Car> getCarList() {
        return new ArrayList<>(carList);
    }

    public void run() {
        carList.forEach(Car::moveForwardCheck);
    }

    public List<Car> getWinnerCarList() {
        Car winnerCar = carList.stream()
                .max(Comparator.comparingInt(Car::getForwardState))
                .orElseThrow();


        return carList.stream()
                .filter(car -> winnerCar.getForwardState() == car.getForwardState())
                .toList();
    }

    public List<Car> getPlyaerRanking() {
        List<Car> arr = List.of(
                new Car("거니" ,8),
                new Car("망주" ,7),
                new Car("앵미" ,6)
        );
//                carList.stream()
//                .sorted(Comparator.comparingInt(Car::getForwardState).reversed())
//                .toList();

        int rank = 2;
        Map<Integer, List<Car>> map = new HashMap<>();
        map.put(1, new ArrayList(List.of(arr.get(0))));
        for (int i = 1; i < arr.size(); i++) {
            Car target = arr.get(i);
            if(target.getForwardState() == arr.get(i - 1).getForwardState()) {
                map.get(map.size()).add(target);
            } else {
                map.put(rank, new ArrayList(List.of(target)));
            }
            rank++;
        }
        System.out.println("map = " + map);

        return null;
    }

    public static void main(String[] args) {
        Cars cars = Cars.from("망주,거니,앵미");
        cars.run();
        cars.run();
        cars.run();
        cars.run();
        cars.run();
        cars.run();
        cars.run();
        cars.run();
        cars.run();
        cars.getPlyaerRanking();
    }
}
