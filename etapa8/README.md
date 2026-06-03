# Etapa 8 — Projeto Final: Código Java + Integração com Banco de Dados

---

## 📋 Descrição

Esta etapa entrega o código Java completo do sistema **Falcão Sombrio**, integrando todos os diagramas UML construídos nas etapas anteriores (Classes, Sequência, Colaboração, Estados e Projeto) com um banco de dados PostgreSQL distribuído hospedado no **Supabase**.

O sistema gerencia uma frota de drones autônomos **Aquila-X**, permitindo operação remota com autenticação biométrica + MFA, navegação por IA, telemetria em tempo real e auditoria imutável com hash SHA-256.

---

## 🗂️ Estrutura de Arquivos

```
falcao_sombrio/
│
├── ConexaoDB.java                  # Configuração JDBC + Supabase
│
├── interfaces/
│   ├── IComandar.java
│   ├── IAutenticavel.java
│   ├── IRegistravel.java
│   ├── IMonitoravel.java
│   └── IAtualizavel.java
│
├── model/
│   ├── Drone.java                  # Classe abstrata
│   ├── DroneAquila.java            # Implementação concreta
│   └── Operador.java
│
├── entity/
│   ├── Missao.java
│   ├── LogAuditoria.java
│   ├── BancoDadosDistribuido.java
│   └── ServidorBackup.java
│
├── service/
│   ├── SistemaNavegacao.java
│   ├── IA_Navegacao.java
│   ├── ServidorControle.java
│   └── Sensor.java                 # + Lidar, Camera, GPS
│
├── security/
│   ├── Autenticacao.java
│   └── ComunicacaoSegura.java
│
├── control/
│   └── CentralControle.java
│
├── dao/
│   ├── OperadorDAO.java
│   ├── DroneDAO.java
│   ├── MissaoDAO.java
│   ├── TelemetriaDAO.java
│   ├── LogAuditoriaDAO.java
│   ├── NavegacaoIADAO.java
│   ├── AutenticacaoDAO.java
│   ├── ComunicacaoSeguraDAO.java
│   ├── SensoresDAO.java
│   ├── InfraestruturaControleDAO.java
│   ├── InfraGerenciaDronesDAO.java
│   └── InfraAutorizaOperadoresDAO.java
│
└── Main.java                       # Ponto de entrada
```

---
