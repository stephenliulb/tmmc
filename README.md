# **Programming: Vertical Line Detection in Images**

This project implements a robust solution for counting vertical lines in images, emphasizing object-oriented design (OOD) principles and externalized configuration for maintainability.

---

## **Overview**
The project demonstrates:
- **Object-Oriented Design (OOD)** through modular architecture
- **Externalized Configuration** for easy maintenance and operational flexibility

---

## **Technologies Used**
- **Oracle OpenJDK 17.0.10**: Compilation and runtime
- **Maven 3.9.6**: Dependency management and executable JAR packaging

---

## **High-Level Design**
### **Key Features**
1. **Extensible Image Processing**
    - `Image` interface for format-agnostic operations (e.g., `JpgImage` implements format-specific logic).

2. **Externalized Configuration**
    - `line_min_length`: Minimum line length (shorter lines are ignored)
    - `background_color`: Customizable background color
    - `line_color`: Configurable line color (default: `rgb(0, 0, 0)`)
    - `roaming_pixel_range`: Tolerance for pixel alignment (improves accuracy for JPEG artifacts)

3. **Modular Line Detection**
    - `VerticalLine` class models detected lines.
    - Design accommodates future extensions (e.g., horizontal line detection).

---

## **Setup Instructions**
### **Prerequisites**
- **Java 17+**
- **Maven 3.9.6+**

### **Execution Steps**
1. **Clone the Repository**:
   ```bash
   https://github.com/stephenliulb/ImageProcessor.git
   cd ImageProcessor



2. **Build (Optional)**:
   Pre-built JAR (image-processor-1.0.jar) is included. To rebuild::
   ```bash
    mvn clean install
   ```

3. **Run the test**:
   - Check the pre-packaged jar file 'image-processor-1.0.jar' under the root folder (ImageProcessor)
   - Test commands
    ```bash
    java -jar image-processor-1.0.jar img_1.jpg
    java -jar image-processor-1.0.jar img_2.jpg
    java -jar image-processor-1.0.jar img_3.jpg
    java -jar image-processor-1.0.jar img_4.jpg
   ```

## **License**
This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
