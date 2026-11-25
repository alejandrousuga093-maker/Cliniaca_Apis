package com.clinica.clinica_api.domain.model;

public class Cita {

    
    private Paciente paciente; //composicion
    private Medico medico;
    private String fecha; // datos propios de la cita
    private String hora;
    private String estado;

    //este es el primer constructor que resive 4 datos
    public Cita(            //primer constructor de la cita
        Paciente paciente,
        Medico medico,
        String fecha,
        String hora
    ) {

       
        this.paciente = paciente; //guardamos los datos de paciente dento de la variable paciente.
        this.medico = medico;      //guarda los datos de medico dentro de la variabole medico 
        this.fecha = fecha;
        this.hora = hora;
        this.estado = "Programada"; //toda cita nueva epieza como "Programada"
    }

    //este es el segundo constructor que resive 5 datos ya que se incluye el estado de la cita para sobrecargarlo 
    public Cita(
        Paciente paciente,
        Medico medico,
        String fecha,
        String hora,
        String estado
    ) {

        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }

    // Getter del paciente.
    public Paciente getPaciente() {
        return paciente;
    }

    // Getter del médico.
    public Medico getMedico() {
        return medico;
    }

    // Getter de la fecha.
    public String getFecha() {
        return fecha;
    }

    // Setter de la fecha.
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    // Getter de la hora.
    public String getHora() {
        return hora;
    }

    
    public void setHora(String hora) { //setter para modificar la hora.
        this.hora = hora;
    }

    
    public String getEstado() {    //getter para obtener de forma controlada el valor de estado.
        return estado;
    }

    
    public void cancelar(String motivo) {  //metodo para cancelar cita.
                                           
        this.estado = "Cancelada";

        System.out.println("Cita cancelada.");
        System.out.println("Motivo: " + motivo);
    }

    // Método para mostrar la información de la cita.
    public void mostrarCita() {

        System.out.println("CITA");
        System.out.println("Paciente: " + paciente.getNombre());
        System.out.println("Médico: " + medico.getNombre());
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println("Estado: " + estado);
    }
}
