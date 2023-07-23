package com.paucar.santana.webapp.model;

import javax.persistence.*;

@Entity
@Table(name = "docentes")
public class Teacher {

    @Id
    @SequenceGenerator(name = "Teacher_sequence", sequenceName = "Teacher_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Teacher_sequence")
    private Integer TeacherId;
    private String nombre;
    private String apellido;
    private Double salario;
    private String ciudad;
    private String direccion;
    private int age;

    public Teacher() {
    }

    /**
     * @return Integer return the TeacherId
     */
    public Integer getTeacherId() {
        return TeacherId;
    }

    /**
     * @param TeacherId the TeacherId to set
     */
    public void setTeacherId(Integer TeacherId) {
        this.TeacherId = TeacherId;
    }

    /**
     * @return String return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return String return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return Double return the salario
     */
    public Double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(Double salario) {
        this.salario = salario;
    }

    /**
     * @return String return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return String return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return int return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

}
