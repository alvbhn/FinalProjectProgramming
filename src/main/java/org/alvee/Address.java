package org.alvee;

import lombok .*;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Address {
    private int streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    public enum Province {
        QC, ON, AB, BC, MB, NB, NL, NS, NT, NU, PE, SK, YT
    }

}