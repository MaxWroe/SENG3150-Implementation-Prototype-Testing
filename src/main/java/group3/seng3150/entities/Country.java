package group3.seng3150.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "Country")
public class Country {


    @Id
    @Column
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String countryCode3;

    @Column(name = "countryName")
    private String name;



}
