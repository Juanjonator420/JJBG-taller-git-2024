package py.edu.uc.lp2.apirest.domains;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private static final long serialVersionUID = -4419931048984717671L;

    private String nombre;
    private String sexo;
    private String apellido;

    private Integer edad;
    private Integer numeroCedula;
    public static final String NACIONALIDAD = "Paraguayo";

    public Person() {
        // TODO Auto-generated constructor stub

    }

    public Person(String nombre, String apellido, Integer edad) {

        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }

    public Person(Integer numeroCedula, String nombre) {
        super();
        this.nombre = nombre;
        this.numeroCedula = numeroCedula;
    }

    public Person(String nombre2, String apellido2, Integer edad2, Integer nroCedula) {
        this(nombre2, apellido2, edad2);
        setNumeroCedula(nroCedula);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(Integer numeroCedula) {
        this.numeroCedula = numeroCedula;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
