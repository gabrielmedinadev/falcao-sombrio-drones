# 🛰️ Falcão Sombrio
### Arquitetura de Software e Modelagem de Banco de Dados

Este repositório contém um **projeto acadêmico desenvolvido na disciplina de Projeto de Software da Universidade Presbiteriana Mackenzie**.

O objetivo do trabalho é realizar a **análise, reformulação da arquitetura de software e modelagem de banco de dados** de um sistema crítico de controle de drones militares autônomos.

O projeto foi desenvolvido com **entregas divididas em fases**, seguindo uma abordagem incremental de engenharia de software ao longo da disciplina.

---

# 📚 Contexto do Projeto

A **Securus Dynamics** é uma empresa multinacional especializada no desenvolvimento de **drones bélicos autônomos para operações militares**.

Seu principal produto, o **Aquila-X**, consiste em uma frota de drones equipados com:

- Inteligência Artificial
- Sensores avançados
- Sistemas de navegação autônoma

Esses drones são capazes de executar:

- Missões táticas
- Reconhecimento em território hostil
- Ataques de precisão

A empresa está desenvolvendo um novo sistema chamado **Falcão Sombrio**, que permitirá a **operação remota e autônoma dos drones através de uma rede de servidores distribuídos e uma interface de controle avançada**.

Após identificar falhas na arquitetura atual, a empresa contratou a consultoria **Cyber Bullet System** para **reformular toda a arquitetura do sistema e definir um novo modelo de banco de dados** capaz de suportar operações críticas.

---

# 🎯 Objetivo do Projeto

Projetar uma **nova arquitetura de software e um modelo de banco de dados robusto** para o sistema **Falcão Sombrio**, garantindo:

- 🔒 Segurança avançada  
- ⚡ Alta disponibilidade  
- 📡 Comunicação em tempo real  
- 📈 Escalabilidade  
- 🛡️ Confiabilidade nas operações dos drones  

O projeto envolve conceitos de:

- Engenharia de Software  
- Sistemas Operacionais  
- Banco de Dados Distribuídos  
- Segurança da Informação  

---

# ⚙️ Funcionalidades do Sistema

## 🖥️ Central de Controle

Interface responsável pelo gerenciamento das frotas de drones.

Funcionalidades:

- Controle remoto e autônomo dos drones  
- Monitoramento de missões  
- Dashboard de telemetria em tempo real  

---

## 🧠 Sistema de Navegação Inteligente

Responsável pela análise do ambiente e tomada de decisão dos drones.

Tecnologias utilizadas:

- **LIDAR**
- **Câmeras**
- **GPS**
- **Redes Neurais**

Capacidades:

- Detecção de ameaças  
- Desvio automático de obstáculos  
- Operação autônoma baseada em IA  

---

## 📡 Gerenciamento de Comunicação

Responsável pela comunicação segura entre drones e a central de controle.

Características:

- Comunicação em tempo real  
- Protocolos seguros de transmissão  
- Sistemas de **fallback** para evitar perda de conexão  

---

## 🗄️ Banco de Dados e Auditoria

Sistema responsável pelo armazenamento e rastreamento das operações.

Características:

- Logs de missões  
- Registro de eventos críticos  
- Criptografia de dados  
- Assinaturas digitais  
- **Banco de dados NoSQL distribuído**

---

## 🛡️ Sistemas Embarcados e Segurança

Os drones utilizam **sistemas operacionais embarcados** capazes de executar múltiplos processos simultaneamente.

Recursos de segurança:

- Autenticação biométrica  
- Autenticação multifator (MFA)  
- Monitoramento de processos do sistema operacional  
- Proteção contra falhas críticas  

---

# 🚨 Problemas Identificados no Sistema Atual

## Arquitetura Deficiente

O sistema atual apresenta:

- Alta latência  
- Interrupções de comunicação durante missões críticas  
- Ausência de **failover automático**

Isso pode tornar drones **inoperantes em caso de falha de servidor**.

---

## Problemas de Segurança

Foram identificadas **tentativas de invasão ao sistema anterior**, exigindo melhorias como:

- Autenticação robusta  
- Criptografia avançada  
- Controle de acesso seguro  

Além disso, os **logs de auditoria devem ser imutáveis e altamente disponíveis**.

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

Os drones executam diversas tarefas simultaneamente:

- Leitura de sensores  
- Processamento de navegação  
- Execução de algoritmos de IA  

O sistema operacional embarcado deve:

- Gerenciar **múltiplas threads**
- Controlar concorrência
- **Priorizar tarefas conforme a criticidade da missão**

---

# 📂 Estrutura do Repositório

O repositório está organizado conforme as **fases de desenvolvimento do projeto**.

Cada fase contém os artefatos produzidos durante o desenvolvimento, como:

- Documentação
- Diagramas
- Modelos arquiteturais
- Modelagem de banco de dados
- Entregas acadêmicas da disciplina

---

# 👨‍💻 Integrantes do Grupo

| Nome | RA |
|-----|-----|
| **Gabriel Medina Peres** | 10426931 |
| **Marcelo Prass Cambé** | 10438275 |

**Turma:** 04G

---

# 🎓 Contexto Acadêmico

**Disciplina:** Projeto de Software  
**Instituição:** Universidade Presbiteriana Mackenzie  

Este projeto possui **finalidade exclusivamente acadêmica** e foi desenvolvido como parte das atividades avaliativas da disciplina.
