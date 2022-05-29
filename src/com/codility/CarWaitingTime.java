package com.codility;

import java.util.LinkedList;

public class CarWaitingTime {

    class Car {
        private int fuelNeeded;
        private int waitTime;
        public Car(int fuelNeeded) {
            this.fuelNeeded = fuelNeeded;
            this.waitTime = 0;
        }
        public int getFuelNeeded() {
            return fuelNeeded;
        }
        public void setFuelNeeded(int fuelNeeded) {
            this.fuelNeeded = fuelNeeded;
        }
        public int getWaitTime() {
            return waitTime;
        }
        public void setWaitTime(int waitTime) {
            this.waitTime = waitTime;
        }
    }
    class Pump {
        private int fuelCapacity;
        private Car fuelingCar;
        public Pump(int fuelCapacity) {
            this.fuelCapacity = fuelCapacity;
        }
        public void setFuelingCar(Car fuelingCar) {
            this.fuelingCar = fuelingCar;
        }
        public int getFuelCapacity() {
            return fuelCapacity;
        }
        public void setFuelCapacity(int fuelCapacity) {
            this.fuelCapacity = fuelCapacity;
        }
        public Car getFuelingCar() {
            return fuelingCar;
        }
    }

    class Station {
        Pump[] pumps = new Pump[3];
        public Station(Pump x, Pump y, Pump z) {
            this.pumps[0] = x;
            this.pumps[1] = y;
            this.pumps[2] = z;
        }
        public int pumpsThatHaveCapacity(int fuelRequired) {
            int count = 0;
            for (Pump p : pumps) {
                if (p.fuelCapacity > fuelRequired)
                    count++;
            }
            return count;
        }
        public Pump getAvailablePump(int fuelRequired) {
            for (Pump p : pumps) {
                if (p.fuelCapacity >= fuelRequired && p.fuelingCar == null)
                    return p;
            }
            return null;
        }
        public boolean isCurrentlyPumpingFuel() {
            for (Pump p : pumps) {
                if (p.fuelingCar != null)
                    return true;
            }
            return false;
        }

        public int minimumFuelNeeded() {
            // what is the min fuel need?
            int minimumFuelNeeded = Integer.MAX_VALUE;
            for (Pump p : pumps) {
                if (p.fuelingCar != null) {
                    minimumFuelNeeded = Math.min(p.fuelingCar.fuelNeeded, minimumFuelNeeded);
                }
            }
            return minimumFuelNeeded;
        }

        public LinkedList<Car> pumpFuel() {
            int minimumFuelNeeded = minimumFuelNeeded();

            LinkedList<Car> finished = new LinkedList<>();
            for (Pump p : pumps) {
                if (p.fuelingCar != null) {
                    p.fuelingCar.fuelNeeded -= minimumFuelNeeded;
                    p.fuelCapacity -= minimumFuelNeeded;

                    if (p.fuelingCar.fuelNeeded <= 0) {
                        finished.add(p.fuelingCar);
                        p.fuelingCar = null;
                    }
                }
            }
            return finished;
        }
    }

    public int solution(int[] A, int X, int Y, int Z) {
        // write your code in Java SE 8

        Pump x = new Pump(X);
        Pump y = new Pump(Y);
        Pump z = new Pump(Z);
        Station station = new Station(x, y, z);
        LinkedList<Car> queue = new LinkedList<>();
        for (int a : A) {
            Car c = new Car(a);
            queue.add(c);
        }
        LinkedList<Car> finished = new LinkedList<>();


        while (queue.size() > 0 || station.isCurrentlyPumpingFuel()) {
            Pump pump = null;
            // if there are cars left
            if (queue.size() > 0) {
                // check if there are pumps that still have capacity for next car
                Car c = queue.peekFirst();
                if (station.pumpsThatHaveCapacity(c.getFuelNeeded()) <= 0) {
                    // if theres none, then
                    return -1;
                }
                pump = station.getAvailablePump(c.getFuelNeeded());
            }

            if (pump != null && queue.size() > 0) {
                pump.fuelingCar = queue.removeFirst();
            }
            // when pump full, cars wait, and so it needs to clear the pumps of cars and add wait times to the remaining cars
            // TODO what about the case of blocked cars?
            else {

                int fuelAmount = station.minimumFuelNeeded();

                LinkedList<Car> done = station.pumpFuel();
                finished.addAll(done);

                for (Car car : queue) {
                    car.setWaitTime(car.getWaitTime() + fuelAmount);
                }
            }

        }

        int max = Integer.MIN_VALUE;
        for (Car c : finished) {
            max = Math.max(c.waitTime, max);
        }
        // return finished.peekLast().getWaitTime();
        return max;
    }

    public static void main(String[] ar) {
        CarWaitingTime c = new CarWaitingTime();
        System.out.println(c.solution(new int[] {2,8,4,3,2}, 7,11,3));
        System.out.println(c.solution(new int[] {5}, 4, 0, 3));
    }

    /**
     * Example test:   ([2, 8, 4, 3, 2], 7, 11, 3)
     * OK
     *
     * Example test:   ([5], 4, 0, 3)
     * OK
     *
     * three_cars (at most three cars)
     * WRONG ANSWER (got -1 expected 0)
     *
     * plenty (each pump has enough fuel to tank all cars, N <= 20)
     * OK
     *
     * trivial_ties (if more pumps are available, we can take any one, N <= 20)
     * OK
     *
     * blocked (some tests with blocked cars, N <= 20)
     * WRONG ANSWER (got -1 expected 2)
     *
     * random (small random tests, N <= 50)
     * OK
     *
     * Detected some errors.
     */
}
