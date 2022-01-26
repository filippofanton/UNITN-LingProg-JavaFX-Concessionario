package it.untin.ldpA.main;
import java.util.LinkedHashSet;

public class Persone extends LinkedHashSet<Persona> {
    public String toString(){
        String ret = "Nome\t-\tCognome\t-\tAnno di nascita\n";
        for(Persona p : this){
            ret += p.toString();
            ret += "\n";
        }
        return ret;
    }

}
