Práctico: Sistema de Gestión de Biblioteca en Java<br>

 ● Objetivo:<br>
 ●    Desarrollar un sistema de gestión de biblioteca aplicando los principios SOLID, utilizando colecciones (Array, ArrayList) y manejando excepciones de forma adecuada.<br>
 
● Requisitos del Sistema<br>
 ● 1. Clases y Funcionalidades Básicas<br>

 ● Libro (Book)<br>
  ○ Atributos:<br>
      ■ ISBN (String, único)<br>
      ■ Título (String)<br>
      ■ Autor (String)<br>
      ■ Año de publicación (int)<br>
      ■ Disponibilidad (boolean)<br>
  ○ Métodos:<br>
      ■Constructor con validación de campos obligatorios.<br>
      ■Getters y Setters con validaciones (ej: año no puede ser negativo).<br>

 ● Repositorio de Libros (BookRepository)<br>
  ○ Debe ser una interfaz con métodos para:<br>
      ■ Agregar un libro (validando ISBN único).<br>
      ■ Eliminar un libro por ISBN.<br>
      ■ Buscar un libro por ISBN.<br>
      ■ Listar todos los libros.<br>
 ○ Implementar dos versiones usando:<br>
   ■ArrayList (ArrayListBookRepository)<br>
   ■Arreglo estático (ArrayBookRepository, con tamaño fijo y manejo de límites).<br>

Gestión de Préstamos (LoanManager)<br>
 ○ Debe permitir:<br>
        ■Prestar un libro (marcar como no disponible).<br>
        ■Devolver un libro (marcar como disponible).<br>
        ■Validar reglas (ej: no prestar un libro ya prestado).<br>
 

2. Principios SOLID<br>
 ● SRP (Single Responsibility Principle):<br>
   ○ Cada clase debe tener una única responsabilidad (ej: BookRepository solo maneja almacenamiento, LoanManager solo préstamos).<br>

● OCP (Open/Closed Principle):<br>
   ○ Usar interfaces (BookFilter) para permitir filtros personalizados (ej: por autor, por año, disponibles).<br>

 ● LSP (Liskov Substitution Principle):<br>
   ○ Si se extiende la clase Book (ej: EBook), debe poder usarse en lugar de la clase base sin romper el sistema.<br>

 ● ISP (Interface Segregation Principle):<br>
   ○ Dividir interfaces grandes en pequeñas (ej: BookRepository y LoanManager separados).<br>

 ● DIP (DependencyInversionPrinciple):<br>
   ○ Las clases de alto nivel (ej: LibraryReportGenerator) deben depender de abstracciones (BookRepository), no de implementaciones concretas.<br>


3. Manejo de Excepciones<br>
  ● Crear una excepción personalizada LibraryException.<br>
  ● Validar casos como:<br>
      ○ ISBN duplicado al agregar un libro.<br>
      ○ Libro no encontrado al eliminar o prestar.<br>
      ○ Intento de prestar un libro ya prestado.<br>
      ○ Campos inválidos (ej: año negativo, ISBN vacío).<br>


4. Colecciones<br>
  ● Usar ArrayList para almacenar libros en ArrayListBookRepository.<br>
  ● Usar arreglos estáticos en ArrayBookRepository (manejando límites con excepciones).<br>
  ● Aplicar Streams para filtrados avanzados (ej: getBooksByAuthor(String author)).<br>