# 🎲 APP RPG

Aplicativo Android desenvolvido com o objetivo de auxiliar e complementar sessões de **RPG de mesa**, reunindo diferentes recursos relacionados à experiência de jogo em um único aplicativo.

> **Projeto em desenvolvimento**
>
> Este projeto está sendo desenvolvido de forma contínua ao longo do curso de **Ciência da Computação**, acompanhando a evolução dos conhecimentos adquiridos durante a disciplina Desenvolvimento Mobile. Novas funcionalidades, melhorias de interface, correções e ajustes serão adicionados conforme o desenvolvimento do projeto avançar.

## Sobre o projeto

O **APP RPG** é um projeto acadêmico desenvolvido para colocar em prática conceitos de desenvolvimento de software e desenvolvimento mobile utilizando **Android Studio, Kotlin e Jetpack Compose**.

A proposta é construir, gradualmente, uma aplicação voltada para jogadores e mestres de RPG, oferecendo recursos que possam ser utilizados durante uma sessão de jogo.

O projeto também serve como ambiente de aprendizado e experimentação, permitindo aplicar conceitos estudados ao longo do curso, como:

* Desenvolvimento de aplicações Android;
* Desenvolvimento de interfaces;
* Arquitetura e organização de software;
* Gerenciamento de estados;
* Componentização;
* Navegação entre telas;
* Uso de bibliotecas e frameworks;
* Controle de versão com Git e GitHub;
* Boas práticas de desenvolvimento.

## Objetivos

O principal objetivo é desenvolver uma aplicação mobile que reúna ferramentas úteis para sessões de RPG em uma interface simples e temática.

Entre os objetivos do projeto estão:

* Criar uma interface voltada para o universo de RPG;
* Desenvolver diferentes funcionalidades para auxiliar jogadores e mestres;
* Organizar os recursos do aplicativo em diferentes telas;
* Aprender e aplicar Jetpack Compose no desenvolvimento de interfaces Android;
* Praticar a organização e manutenção de um projeto Android;
* Evoluir o projeto progressivamente conforme novos conteúdos forem estudados na faculdade.

## Funcionalidades

Atualmente, o aplicativo está sendo desenvolvido e algumas funcionalidades encontram-se em diferentes estágios de implementação.

### Dados

Área destinada às funcionalidades relacionadas a rolagem de dados para RPG.

### Ficha de personagem

Tela destinada à visualização e gerenciamento das informações de um personagem.

### Músicas

Tela destinada à organização de músicas temáticas que podem ser utilizadas durante as sessões de RPG.

A tela atualmente conta com:

* Lista de músicas;
* Campo de pesquisa;
* Filtros por gênero musical;
* Espaço reservado para imagem/capa da música;
* Interface temática;
* Navegação pela barra inferior.

## Interface

O aplicativo utiliza uma identidade visual inspirada em uma estética de **RPG fantasy/dark fantasy**, utilizando principalmente tons de roxo, laranja, cinza e preto.

A interface está sendo construída utilizando **Jetpack Compose**, permitindo que os componentes sejam desenvolvidos de forma declarativa e reutilizável.

A paleta visual atualmente possui cores destinadas a diferentes funções da aplicação, incluindo:

* 🟣 Elementos relacionados ao mestre;
* 🟠 Elementos relacionados ao jogador;
* ⚫ Fundos e áreas estruturais;
* ⚪ Textos e elementos de destaque.

## Tecnologias utilizadas

O projeto utiliza atualmente:

* **Kotlin**;
* **Android Studio**;
* **Jetpack Compose**;
* **Material 3**;
* **Gradle**;
* **Git**;
* **GitHub**.

A escolha dessas tecnologias também faz parte do processo de aprendizagem e evolução do projeto durante a graduação.

## Estrutura do projeto

A estrutura do projeto segue o padrão de uma aplicação Android utilizando Gradle e Kotlin.

De forma geral:

```text
app_rpg/
│
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── ...
│           │
│           └── res/
│               ├── drawable/
│               ├── mipmap/
│               └── values/
│
├── gradle/
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── settings.gradle.kts
└── README.md
```

A organização interna poderá ser modificada conforme novas funcionalidades forem adicionadas ao aplicativo.

## Navegação

O aplicativo possui uma navegação inferior que organiza as principais áreas da aplicação.

Atualmente, estão implementadas áreas para:

```text
┌───────────────────────────────────┐
│                                   │
│              CONTEÚDO             │
│                                   │
│                                   │
├───────────────────────────────────┤
│                                   │
│   Dados     Ficha      Música     │
└───────────────────────────────────┘
```

A navegação e as telas estão sendo desenvolvidas utilizando componentes do **Jetpack Compose**.

## Contexto acadêmico

Este projeto faz parte do processo de aprendizado durante o curso de **Ciência da Computação**.

Dessa forma, o repositório também representa a evolução técnica do projeto ao longo da graduação.

## Como executar o projeto

### Pré-requisitos

Para executar o projeto, recomenda-se ter instalado:

* Android Studio;
* JDK compatível com a versão utilizada pelo projeto;
* Android SDK;
* Um dispositivo Android ou emulador.

### Clonando o repositório

```bash
git clone https://github.com/4rt3mis4D4/app_rpg.git
```

Entre na pasta:

```bash
cd app_rpg
```

Abra o projeto no **Android Studio**, aguarde a sincronização do Gradle e execute a aplicação em um emulador ou dispositivo Android.

## Controle de versão

O desenvolvimento do projeto utiliza **Git** para controle de versão e **GitHub** para hospedagem do código-fonte.

Os commits são utilizados para registrar a evolução das funcionalidades e alterações realizadas durante o desenvolvimento.

Exemplo:

```bash
git add .
git commit -m "feat: cria tela de músicas"
git push
```

## Autores

Desenvolvido por **Bruno, Gabriela e Michelle** como projeto acadêmico e de aprendizado durante o curso de **Ciência da Computação**.

---
