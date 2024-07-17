package com.proyectoDeAlura.FOROHUB.Topico;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String autor;
    private String curso;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "fechadecreacion")
    private LocalDateTime fechaDeCreacion;


    public Topico(RegistroTopicos registroTopicos) {
        this.titulo = registroTopicos.titulo();
        this.mensaje = registroTopicos.mensaje();
        this.autor = registroTopicos.autor();
        this.curso = registroTopicos.curso();
    }

    public void actualizarTopico(ListadoTopico listadoTopico) {
        if(listadoTopico.titulo() != null){
            this.titulo = listadoTopico.titulo();
        }
        if(listadoTopico.mensaje() != null){
            this.mensaje = listadoTopico.mensaje();
        }
        if(listadoTopico.autor() != null){
            this.autor = listadoTopico.autor();
        }
        if (listadoTopico.curso() != null){
            this.curso = listadoTopico.curso();
        }


    }
}
