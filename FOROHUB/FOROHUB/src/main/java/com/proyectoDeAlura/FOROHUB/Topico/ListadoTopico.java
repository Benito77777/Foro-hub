package com.proyectoDeAlura.FOROHUB.Topico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ListadoTopico(String titulo, String mensaje, String autor, String curso, LocalDateTime fechaDeCreacion) {
}
