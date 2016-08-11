/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package radet.publisher;

/**
 *
 * @author Titu
 */
public class Address {

    private final int sector;
    private final String street;
    private final String number;
    private final String block;

    public Address(int sector, String street, String number, String block) {
        if (sector < 1 && sector > 6) {
            throw new IllegalArgumentException("sector " + sector);
        }
        if (street == null || street.trim().isEmpty()) {
            throw new IllegalArgumentException("street " + street);
        }
        if ((number == null || number.trim().isEmpty()) && (block == null || block.trim().isEmpty())) {
            throw new IllegalArgumentException("number and block are null or empty");
        }
        this.sector = sector;
        this.street = street;
        this.number = number;
        this.block = block;
    }

    public int getSector() {
        return sector;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getBlock() {
        return block;
    }

}
