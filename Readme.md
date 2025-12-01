# 📘 Calculadora JavaFX

Aplicación de calculadora desarrollada en **Java** utilizando **JavaFX**, como parte de mi proceso de aprendizaje continuo y la búsqueda de nuevos desafíos técnicos.

El objetivo principal de este proyecto fue profundizar en:

- Desarrollo de interfaces gráficas con JavaFX.
- Manejo de eventos, controladores y vistas.
- Integración entre **FXML**, **CSS**, Y **Java**.
- Buenas prácticas de estructuración y escalabilidad. 

---

## 🚀 Características principales

- Barra de título personalizada con iconos propios 
- Evaluación completa de expresiones:
    - Números `0-9`
    - Paréntesis `(`,`)`
    - `+`, `−`, `×`, `÷`
- Funciones adicionales:
    - `DEL` (borrar último carácter)
    - `ANS` (último resultado)  

---

## 🛠 Tecnologías utilizadas

- **Java 17+**
- **JavaFX 21**
- **Maven** como gestor de dependencias
- **FXML** para estructura visual
- **CSS** para estilos personalizados

---
## ▶ Cómo ejecutar el proyecto

Clonar el repositorio:

```bash
git clone https://github.com/TU_USUARIO/calculadora-javafx.git
cd calculadora-javafx
```

Ejecutar con Maven:

```bash
mvn clean javafx:run
```

O desde IntelliJ IDEA, ejecutar la clase `App`.

---

## 📂 Estructura del proyecto

```
src/
 ├─ main/
 │   ├─ java/
 │   │   ├─ calculadorafx/
 │   │   │   ├─ app/       → Clase App (punto de entrada)
 │   │   │   └─ controller → Lógica de UI y manejo de eventos
 │   │   └─ model/         → Evaluador matemático
 │   └─ resources/
 │       ├─ view/          → Archivos FXML
 │       ├─ css/           → Estilos de la interfaz
 │       └─ icons/         → Iconos personalizados
 └─ test/                  → (opcional)
```

---
## 🌱 Próximas mejoras (ideas futuras)

- Modo científico (raíces, potencias, trigonometría)
- Soporte para teclado físico
- Historial de expresiones
- temas (light/dark)

---

## 📸 Capturas

[![345shots-so.png](https://i.postimg.cc/hGwjXKWv/345shots-so.png)](https://postimg.cc/qts0YV7H)