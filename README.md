# LinguaFlash

![image](https://github.com/serhiibh/lingua-flash/assets/8862639/396c4d3c-57c1-445e-b8d0-26356f9ff47a)

LinguaFlash is a desktop application designed to aid language learning enthusiasts in expanding their vocabulary in a fun and interactive way. 
Built with JavaFX and powered by Spring Boot, the application integrates with third-party 
APIs to fetch vocabulary and provide translations, making it a comprehensive tool for language immersion.

## Features

- **Vocabulary Learning**: View flashcards of words with their forms, short descriptions, and detailed explanations.
- **Translation**: Instantly translate words and descriptions to understand their meanings in different languages.
- **Intuitive Interface**: A clean and simple UI ensures a focus on learning without distractions.
- **Customizable Language Selection**: Choose which language you want to learn and translate to. (TODO)

## Running the Built Application

If you have downloaded the built JAR, you can run the application without building from source. Ensure you have Java JDK 11 or higher installed on your system. Follow these steps to run `lingua-flash`:

1. Navigate to the directory containing the downloaded JAR file. If you've just cloned the repository, it might look like this:
```bash
cd /builds/
```
2. Run the application using the following command:
```bash
java -jar lingua-flash-0.0.1.jar
```
After running this command, the application should start, and you can begin using it immediately.

## Getting Started

To get a local copy up and running, follow these simple steps.

### Prerequisites

- Java JDK 11 or higher
- Maven

### Installation

1. Clone the repository:
```bash
git clone https://github.com/your-username/lingua-flash.git
```
2. Navigate to the project directory:
```bash
cd lingua-flash
```
3. Build the project with Maven:
```bash
mvn clean install
```
4. Run the application:
```bash
mvn spring-boot:run
```

### Usage
Upon launching LinguaFlash, you will be greeted with a word card. Click 'Next' to cycle through different words, and use the 'Translate' button to view the word's translation in the selected language.

### Built With
JavaFX - The client application framework
Spring Boot - The server framework
Maven - Dependency Management

### Open Questions and Issues
We are currently facing the following challenges and would welcome any contributions or suggestions:
* Finding a Free Translation Provider: It has been challenging to find a free translation provider offering developers a generous API. Any suggestions for services or workarounds would be greatly appreciated.

## License
Distributed under the MIT License. See LICENSE for more information.

## Contact
Serhii Bohutskyi - serhii.bohutskyi@gmail.com

Project Link: https://github.com/serhiibh/lingua-flash

