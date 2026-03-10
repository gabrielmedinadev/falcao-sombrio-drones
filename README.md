# Falcão Sombrio – Arquitetura de Software e Modelagem de Banco de Dados

Este repositório contém um **projeto acadêmico desenvolvido na disciplina de Projeto de Software da Universidade Presbiteriana Mackenzie**. O trabalho consiste na **análise, reformulação arquitetural e modelagem de banco de dados** para um sistema complexo de operação de drones militares autônomos.

O projeto foi desenvolvido com **entregas divididas em fases ao longo da disciplina**, seguindo uma abordagem incremental de engenharia de software. Cada fase representa uma etapa da evolução da solução proposta, incluindo análise de requisitos, modelagem arquitetural, definição de banco de dados e documentação técnica.

---

# Contexto do Projeto

A **Securus Dynamics** é uma empresa multinacional especializada no desenvolvimento de **drones bélicos autônomos para operações militares**. Seu principal produto, o **Aquila-X**, consiste em uma frota de drones equipados com inteligência artificial e sensores avançados capazes de realizar missões táticas, reconhecimento em território hostil e ataques de precisão.

A empresa está desenvolvendo um novo sistema chamado **Falcão Sombrio**, que permitirá a **operação remota e autônoma dos drones através de uma rede de servidores distribuídos e uma interface operacional avançada**.

Durante o desenvolvimento, a diretoria identificou diversas falhas na arquitetura atual do sistema, incluindo problemas de **latência, segurança e disponibilidade**. Para resolver essas questões, a consultoria **Cyber Bullet System** foi contratada para **reformular a arquitetura de software e propor um novo modelo de banco de dados** capaz de suportar operações críticas em tempo real.

---

# Objetivo do Projeto

O objetivo deste projeto é **projetar uma nova arquitetura de software e um modelo de banco de dados robusto para o sistema Falcão Sombrio**, garantindo:

- Maior segurança  
- Alta disponibilidade  
- Escalabilidade  
- Confiabilidade nas operações dos drones  

O trabalho envolve conceitos importantes de:

- Engenharia de Software  
- Sistemas Operacionais  
- Banco de Dados Distribuídos  
- Segurança de Sistemas  

---

# Principais Funcionalidades do Sistema

## Central de Controle

Interface responsável pelo gerenciamento das frotas de drones, permitindo:

- Controle remoto e autônomo dos drones  
- Visualização de dados em tempo real  
- Dashboard de telemetria das missões  

---

## Sistema de Navegação Inteligente

Responsável pelo sensoriamento e tomada de decisões do drone.

Tecnologias utilizadas:

- **LIDAR**
- **Câmeras**
- **GPS**
- **Redes neurais**

Funções principais:

- Detecção de ameaças  
- Desvio automático de obstáculos  
- Operação autônoma baseada em IA  

---

## Gerenciamento de Comunicação

Responsável pela comunicação segura entre a central de controle e os drones.

Características:

- Protocolos de comunicação em tempo real  
- Comunicação criptografada  
- Sistemas de **fallback** para evitar perda de conexão durante missões  

---

## Banco de Dados e Auditoria

Sistema responsável pelo armazenamento e registro das operações do sistema.

Características:

- Logs de missões realizadas  
- Registro de eventos críticos  
- Criptografia de dados  
- Assinaturas digitais  
- Uso de **banco de dados NoSQL distribuído**  

---

## Sistemas Embarcados e Segurança

Os drones utilizam **sistemas operacionais embarcados** capazes de gerenciar múltiplos processos simultaneamente.

Recursos de segurança incluem:

- Autenticação biométrica  
- Autenticação multifator (MFA)  
- Monitoramento de processos do sistema operacional  
- Proteção contra falhas críticas  

---

# Problemas Identificados no Sistema Atual

## Arquitetura Deficiente

O sistema atual apresenta:

- Alta latência  
- Interrupções de comunicação durante missões críticas  
- Falta de **failover automático**  

Como consequência, drones podem se tornar **inoperantes em caso de falha de servidor**.

---

## Problemas de Segurança

Foram identificadas **tentativas de invasão ao sistema anterior**, o que exige melhorias como:

- Autenticação robusta  
- Criptografia avançada  
- Controle de acesso seguro  

Além disso, os **logs de auditoria precisam ser imutáveis e altamente disponíveis**.

---

## Gerenciamento de Banco de Dados

O sistema precisa garantir:

- Sincronização de dados em tempo real  
- Integridade das informações transmitidas pelos drones  
- Armazenamento histórico das missões  

Esses dados serão utilizados para:

- Auditorias  
- Análises estratégicas  
- Análise preditiva  

A solução proposta utiliza **banco de dados distribuído com replicação**.

---

## Sistemas Operacionais e Concorrência

Os drones executam diversas tarefas simultaneamente, como:

- Leitura de sensores  
- Processamento de navegação  
- Execução de algoritmos de IA  

O sistema operacional embarcado deve ser capaz de:

- Gerenciar **múltiplas threads**  
- Controlar concorrência entre processos  
- **Priorizar tarefas conforme a criticidade da missão**

---

# Estrutura do Repositório

O repositório está organizado conforme as **fases de desenvolvimento do projeto acadêmico**.

Cada fase contém os artefatos produzidos durante o desenvolvimento, como:

- Documentação  
- Diagramas  
- Modelos arquiteturais  
- Modelagem de banco de dados  
- Entregas solicitadas na disciplina  

---

# Integrantes do Grupo

- **Gabriel Medina Peres** - RA: 10426931  
- **Marcelo Prass Cambé** - RA: 10438275  

**Turma:** 04G  

---

# Contexto Acadêmico

**Disciplina:** Projeto de Software  
**Instituição:** Universidade Presbiteriana Mackenzie  

Este projeto possui **finalidade exclusivamente acadêmica** e foi desenvolvido como parte das atividades avaliativas da disciplina.

O conteúdo presente neste repositório representa a **aplicação prática de conceitos de engenharia de software em um cenário hipotético de sistemas críticos distribuídos**.
