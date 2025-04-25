# Nth Finder API

**Spring Boot приложение для поиска N-го минимального числа в Excel файлах (xlsx)**  
✅ **Эффективный алгоритм `O(M * log N)` без сортировки**  
✅ **Swagger UI для тестирования API**  
✅ **JUnit тесты**  
✅ **Docker контейнер**  
✅ **CI/CD через GitHub Actions**

---

## **Функционал**
1. **Загружает Excel файл (xlsx)** и читает числа из первого столбца.
2. **Находит N-е минимальное число** за `O(M * log N)`, где `M` — количество строк.
3. **REST API со Swagger** для удобного тестирования.
4. **Логирование в файл** через Logback.
5. **Автоматические тесты** (JUnit).

---

## **Технологии**
- **Java 17**
- **Spring Boot 3.2.0**
- **Apache POI 5.2.3** (чтение xlsx)
- **Springdoc OpenAPI** (Swagger 3)
- **Logback** (логирование)
- **JUnit 5** (тесты)

---

## **Как запустить проект?**

1. Клонирование репозитория
```bash
git clone https://github.com/ваш-аккаунт/nth-finder.git
cd nth-finder

2. Сборка проекта (Maven)

mvn clean package

3. Запуск приложения
java -jar target/nth-finder-0.0.1-SNAPSHOT.jar

4. Swagger UI
Открой: http://localhost:8081/swagger-ui/index.html
Метод: POST /api/find-nth-min
Параметры:

filePath → путь к Excel файлу (например, C:\numbers.xlsx).
n → позиция числа (например, 5).