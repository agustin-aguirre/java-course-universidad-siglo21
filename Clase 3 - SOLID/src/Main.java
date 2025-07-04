public class Main {

}


// Subir a youtube ?
// ● Práctico: Sistema de Gestión de Biblioteca en Java
// ● Objetivo:
// ●    Desarrollar un sistema de gestión de biblioteca aplicando los principios SOLID, utilizando colecciones (Array, ArrayList) y manejando excepciones de forma adecuada.
// ● Requisitos del Sistema

// ● 1. Clases y Funcionalidades Básicas

// ● Libro (Book)
//  ○ Atributos:
//      ■ ISBN (String, único)
//      ■ Título (String)
//      ■ Autor (String)
//      ■ Año de publicación (int)
//      ■ Disponibilidad (boolean)
//  ○ Métodos:
//      ■Constructor con validación de campos obligatorios.
//      ■Getters y Setters con validaciones (ej: año no puede ser negativo).

// ● Repositorio de Libros (BookRepository)
//  ○ Debe ser una interfaz con métodos para:
//      ■ Agregar un libro (validando ISBN único).
//      ■ Eliminar un libro por ISBN.
//      ■ Buscar un libro por ISBN.
//      ■ Listar todos los libros.

// ○ Implementar dos versiones usando:
//   ■ArrayList (ArrayListBookRepository)
//   ■Arreglo estático (ArrayBookRepository, con tamaño fijo y manejo de límites).

// ● Gestión de Préstamos (LoanManager)
// ○ Debe permitir:
//        ■Prestar un libro (marcar como no disponible).
//        ■Devolver un libro (marcar como disponible).
//        ■Validar reglas (ej: no prestar un libro ya prestado).


// ● 2. Principios SOLID
// ● SRP (Single Responsibility Principle):
//   ○ Cada clase debe tener una única responsabilidad (ej: BookRepository solo maneja almacenamiento, LoanManager solo préstamos).
// ● OCP (Open/Closed Principle):
//   ○ Usar interfaces (BookFilter) para permitir filtros personalizados (ej: por autor, por año, disponibles).
// ● LSP (Liskov Substitution Principle):
//   ○ Si se extiende la clase Book (ej: EBook), debe poder usarse en lugar de la clase base sin romper el sistema.
// ● ISP (Interface Segregation Principle):
//   ○ Dividir interfaces grandes en pequeñas (ej: BookRepository y LoanManager separados).
// ● DIP (DependencyInversionPrinciple):
//   ○ Las clases de alto nivel (ej: LibraryReportGenerator) deben depender de abstracciones (BookRepository), no de implementaciones concretas.

//● 3. Manejo de Excepciones
//  ● Crear una excepción personalizada LibraryException.

//● Validar casos como:
//  ○ ISBN duplicado al agregar un libro.
//  ○ Libro no encontrado al eliminar o prestar.
//  ○ Intento de prestar un libro ya prestado.
//  ○ Campos inválidos (ej: año negativo, ISBN vacío).


//● 4. Colecciones
//  ● Usar ArrayList para almacenar libros en ArrayListBookRepository.
//  ● Usar arreglos estáticos en ArrayBookRepository (manejando límites con excepciones).
//  ● Aplicar Streams para filtrados avanzados (ej: getBooksByAuthor(String author)).



// Entrega: Armar un video en Youtube explicando el código fuente y ejecutando
// el mismo. En la explicación es necesario que expliquen los conceptos teóricos
// empleados. También deben enviar el código.
// Los que manejan el concepto GIT lo pueden hacer por ahí, sino en archivos está bien.
// Plazo de entrega máximo  lunes 7/07, 9.30 hs