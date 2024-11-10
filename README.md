PasswordPanel - Panel de Generación y Seguridad de Contraseñas
PasswordPanel es un componente de Java Swing diseñado para crear y gestionar contraseñas con opciones de visibilidad, generación automática y evaluación de seguridad. Este panel incluye un campo de entrada para contraseñas, iconos interactivos para mostrar/ocultar el texto, generar una contraseña aleatoria y un indicador visual de la seguridad de la contraseña.

Características principales
Campo de Contraseña: Usa JPasswordField para la entrada de contraseñas, con un tamaño predeterminado de 15 caracteres. Permite alternar entre la visibilidad y el modo oculto de la contraseña.

Alternar Visibilidad de Contraseña: Usa un ícono que permite al usuario alternar entre ver y ocultar la contraseña mediante un clic.

Generación de Contraseñas Aleatorias: Incluye un generador de contraseñas que permite especificar la longitud y los tipos de caracteres (mayúsculas, minúsculas, números y caracteres especiales).

Evaluación de Seguridad de la Contraseña: Evalúa el nivel de seguridad de la contraseña y lo muestra visualmente mediante un mensaje de texto que cambia de color según el nivel de seguridad (baja, media o fuerte).

Componentes del Código
Configuración de Imágenes:

![image](https://github.com/user-attachments/assets/bf439d72-0b94-407e-b5e5-bfd867ebee87)

Carga tres imágenes (mostrar, ocultar y generar) para los iconos de alternar visibilidad y generar contraseña.

Campo de Contraseña (JPasswordField):

![image](https://github.com/user-attachments/assets/f9cdec7d-cbda-42f0-a3eb-5c61bba68002)

Usa JPasswordField para la entrada de la contraseña, con el texto oculto por asteriscos (*) de manera predeterminada.
Añade un DocumentListener que actualiza el nivel de seguridad de la contraseña cada vez que se modifica el texto en el campo.

Icono de Alternar Visibilidad:

![image](https://github.com/user-attachments/assets/69526f48-d474-4bc5-8603-e10a6d282636)

Al hacer clic en este icono, se muestra o esconde el texto de la contraseña y se cambia la imagen entre "mostrar" y "ocultar".

Icono de Generación de Contraseña:

![image](https://github.com/user-attachments/assets/dfbae89d-a4ed-4997-b69d-1d5dd3f80234)

Al hacer clic en este icono, se genera una nueva contraseña aleatoria en función de la configuración actual del tipo de caracteres y longitud.

Etiqueta de Seguridad:

![image](https://github.com/user-attachments/assets/cb4ccf83-e1e2-42db-97bb-6b7875e61686)

Indica la seguridad de la contraseña (baja, media o fuerte) en función de un análisis de características como longitud y tipos de caracteres.


Configuración y Métodos

Configuración de Longitud de Contraseña (setLongitud): Define la longitud de la contraseña generada, con un valor predeterminado de 8 caracteres.

Selección de Tipos de Caracteres: Métodos setIncluirMayusculas, setIncluirMinusculas, setIncluirNumeros y setIncluirEspeciales para habilitar o deshabilitar cada tipo de carácter en la generación de contraseñas.


![image](https://github.com/user-attachments/assets/1a282c71-6443-43ef-b4c1-6814c4486567)


Alternar Visibilidad (togglePasswordVisibility):

![image](https://github.com/user-attachments/assets/fcb2a6a4-e64d-4428-a584-b2d47ae46592)

Muestra u oculta el texto de la contraseña según el estado actual.

Generación de Contraseña Aleatoria (generatePassword):

![image](https://github.com/user-attachments/assets/3a6d50fb-8bad-45ac-9fa5-8cfbe34dd047)

Crea una contraseña aleatoria en base a los tipos de caracteres seleccionados.

El método generateRandomPassword: 

![image](https://github.com/user-attachments/assets/199f99f6-613a-4349-a827-a91c31dc9021)

Se encarga de construir una cadena de caracteres permitidos y generar una contraseña de la longitud especificada.

Evaluación de Seguridad de la Contraseña (updatePasswordSecurity):

![image](https://github.com/user-attachments/assets/470140e9-87e4-47be-95ed-8ffa6887b8f6)

Usa el método calculatePasswordSecurity: 

![image](https://github.com/user-attachments/assets/9701fcc7-173d-472b-9f32-eaa1b1f60494)

Para evaluar características como longitud, inclusión de mayúsculas, minúsculas, números y caracteres especiales.
La seguridad se clasifica en "Baja", "Media" o "Fuerte" y se indica visualmente cambiando el color del texto de la etiqueta seguridadLabel.
