

public class Main {

    public static void main(String[] args) {

        String [] NStops = {"Times Square", "34th", "28th", "23rd", "Union Square", "8th"};
        String [] LStops = {"8th", "6th", "Union Square", "3rd", "1st"};
        String [] sixStops = {"Grand Central", "33rd", "28th", "23rd", "Union Square", "Astor Place"};

        Line N = new Line("N",NStops);
        Line L = new Line("L",LStops);
        Line six = new Line("6", sixStops);

        System system = new System();
        system.addLine(N);
        system.addLine(L);
        system.addLine(six);


        // Example
        system.planTrip("N", "Times Square", "6", "33rd");
        // system.planTrip("N", "Times Square", "L", "1st");
        //  system.planTrip("L", "1st", "L", "6th"); //same line
        // system.planTrip("6", "23rd", "6", "Grand Central"); //reverse way
    }


}