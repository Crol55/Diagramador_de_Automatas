SET JAVA_HOME="C:\Program Files\Java\jdk1.8.0_121\bin"
SET PATH=%JAVA_HOME%;%PATH%
SET CLASSPATH=%JAVA_HOME%;
SET JFLEX_HOME= C:\jflex-1.6.1
cd C:\Users\carlo\OneDrive\Documentos\NetBeansProjects\ProjectF\src\projectf
java -jar %JFLEX_HOME%\lib\jflex-1.6.1.jar lexico.jflex
pause