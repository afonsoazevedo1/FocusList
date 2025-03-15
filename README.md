# FocusList - Lista de Tarefas

O **FocusList** é um aplicativo Android desenvolvido para ajudar na organização e produtividade, permitindo que você adicione, edite, marque como concluídas e remova tarefas facilmente. A interface foi construída usando Jetpack Compose para proporcionar uma experiência de usuário fluida e moderna.

## Funcionalidades

- **Adicionar Tarefas**: Adicione novas tarefas com títulos e descrições.
- **Marcar como Concluída**: Marque suas tarefas como feitas ao completá-las.
- **Remover Tarefas**: Exclua tarefas que não são mais necessárias.
- **Editar Tarefas**: Atualize tarefas existentes com novos detalhes ou prazos.
- **Interface Moderna**: Utiliza Jetpack Compose para uma interface de usuário limpa e intuitiva.

## Arquitetura

O app segue a arquitetura **MVVM (Model-View-ViewModel)**, que separa as responsabilidades de cada camada, tornando o código mais organizado, escalável e fácil de testar.

- **Model**: Contém a lógica de negócios e os dados do app.
- **View**: A interface do usuário, construída usando Jetpack Compose.
- **ViewModel**: Contém a lógica de controle da UI e interage com o Model.

## Instalação

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seuusuario/focuslist.git
