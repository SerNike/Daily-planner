# Daily-planner
Сервис по управлению задачами 

Описание приложения
Консольное приложение, в которое можно заносить новые задачи через консоль, удалять задачи, получать список всех задач на день с помощью методов.

Каждая задача обязательно имеет заголовок. У каждой задачи может быть поле для описания. Также, все задачи разделены по типу: личные или рабочие задачи. У каждой задачи есть дата и время, которые были присвоены при создании.

Для более гибкого управления задачами есть поле id.

Кроме того, в ежедневнике задачи разная повторяемость, которую можно указать при создании задачи:

- однократная,
- ежедневная,
- еженедельная,
- ежемесячная,
- ежегодная.
У каждой повторяемости есть метод для получения следующей даты и времени выполнения.

Все задачи хранятся в коллекции Map с ключом в виде id задачи.

Управление задачами осуществляется через консоль и класс Scanner.

# Использован следующий стек технологий:
Java8
