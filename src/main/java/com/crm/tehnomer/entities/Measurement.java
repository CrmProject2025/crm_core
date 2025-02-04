package com.crm.tehnomer.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int value;

    // приходит с другого сервера
    @Column(nullable = false)
    private LocalDateTime measurementDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meter_id")
    private Meter meter;

    public Measurement(int value, LocalDateTime measurementDate) {
        this.value = value;
        this.measurementDate = measurementDate;
    }

    public Measurement() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Measurement other = (Measurement) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Measurement [id=" + id + ", value=" + value + ", measurementDate=" + measurementDate + ", meter="
                + meter + "]";
    }



    
}
