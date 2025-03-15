FocusList - Lista de Tarefas 
O FocusList é um aplicativo Android desenvolvido para ajudar na organização e produtividade, permitindo que você adicione, edite, marque como concluídas e remova tarefas facilmente. A interface foi construída usando Jetpack Compose para proporcionar uma experiência de usuário fluida e moderna.

Funcionalidades
    Adicionar Tarefas: Adicione novas tarefas com títulos e descrições.
    Marcar como Concluída: Marque suas tarefas como feitas ao completá-las.
    Remover Tarefas: Exclua tarefas que não são mais necessárias.
    Editar Tarefas: Atualize tarefas existentes com novos detalhes ou prazos.
    Interface Moderna: Utiliza Jetpack Compose para uma interface de usuário limpa e intuitiva.
Arquitetura
    O app segue a arquitetura MVVM (Model-View-ViewModel), que separa as responsabilidades de cada camada, tornando o código mais organizado, escalável e fácil de testar.

Model: Contém a lógica de negócios e os dados do app.
View: A interface do usuário, construída usando Jetpack Compose.
ViewModel: Contém a lógica de controle da UI e interage com o Model.
Instalação:

1. Clone o repositório:
    git clone https://github.com/seuusuario/focuslist.git

2. Abra o projeto no Android Studio:
    Abra o Android Studio e selecione "Open an existing project".
    Navegue até a pasta do projeto clonado e abra.

3. Certifique-se de que o Android Studio está configurado corretamente e instale as dependências:
O Android Studio irá detectar as dependências automaticamente.
    Se necessário, sincronize o Gradle.
    
4. Execute o aplicativo:
    Conecte um dispositivo Android ou inicie um emulador.
    Clique em "Run" no Android Studio.

5. Tecnologias Utilizadas
    Android Nativo: Desenvolvido usando Kotlin.
    Arquitetura MVVM: Seguindo o padrão de arquitetura Model-View-ViewModel.
    Jetpack Compose: Framework moderno para construção de interfaces de usuário.
    Room (opcional, caso esteja utilizando para persistência de dados): Banco de dados local para armazenamento de tarefas.

6. Contribuindo
   Faça um fork deste repositório.
   Crie uma branch com a sua feature (git checkout -b feature/feature-name).
   Commit suas mudanças (git commit -am 'Add new feature').
   Faça o push para a branch (git push origin feature/feature-name).
   Abra um pull request.
