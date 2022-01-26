package it.untin.ldpA.main;

import java.util.Objects;

public abstract class Record{
    String field1, field2;
    int field3;

    public Record(String one, String two, int three){
        field1=one;
        field2=two;
        field3=three;
    }

    public String toString(){
        return this.field1 + "\t-\t" + this.field2 + "\t-\t" + this.field3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Record)) return false;
        Record record = (Record) o;
        return field3 == record.field3 && field1.equals(record.field1) && field2.equals(record.field2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field1, field2, field3);
    }
}
