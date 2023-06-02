package com.example.triparrangersfyp.model;

public class Seat {

    private int SeatNo;
    private boolean avaialble = false;

    public Seat() {
    }

    public Seat(int seatNo, boolean avaialble) {
        SeatNo = seatNo;
        this.avaialble = avaialble;
    }

    public Seat(String b_seats, boolean avaialble) {
    }

    public int getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(int seatNo) {
        SeatNo = seatNo;
    }

    public boolean isAvaialble() {
        return avaialble;
    }

    public void setAvaialble(boolean avaialble) {
        this.avaialble = avaialble;
    }
}
