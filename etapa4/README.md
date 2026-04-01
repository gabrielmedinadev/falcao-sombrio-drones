# Etapa 4 – Integração de Modelos (Classes + Banco de Dados)

Esta etapa mostra a integração entre o **modelo de classes UML** e o **modelo relacional no SQLite**.

---

## 📌 Estrutura do Banco (SQLite)


Ele cria as seguintes tabelas:

- **Operadores**
- **Drones**
- **Missões**
- **Sensores**
- **Telemetria**
- **Logs de auditoria**
- **Autenticação**

Além disso, foram adicionadas restrições de integridade, como:
- **CHECK** em atributos (ex.: status, perfil, bateria).  
- **Chaves estrangeiras** respeitando os relacionamentos UML.  
- **Índice único** para evitar missões sobrepostas no mesmo drone e horário.  

---

## 📸 Evidências (Prints)

- **Estrutura do Banco**  
  <img width="1910" height="343" alt="EstruturaBanco" src="https://github.com/user-attachments/assets/fd8bfa9b-c7f8-456f-910b-abf1d89b2f42" />

- **Operadores**  
  <img width="712" height="124" alt="Operador" src="https://github.com/user-attachments/assets/17a90cc5-392a-4917-a9bc-7aea57b153bb" />

- **Drones**  
  <img width="709" height="97" alt="Drones" src="https://github.com/user-attachments/assets/ae3d0dc4-1383-4e94-a9a7-863be04c6caf" />

- **Missões**  
  <img width="712" height="100" alt="Missao" src="https://github.com/user-attachments/assets/b189e2e0-3ca1-41ca-a192-99eccc835b66" />

- **Sensores**

  <img width="712" height="149" alt="Sensor" src="https://github.com/user-attachments/assets/64a2ec8c-f0fe-45c0-9a33-a0e788c1c2e5" />



- **Telemetria**

  <img width="714" height="116" alt="Tele" src="https://github.com/user-attachments/assets/ddc3c281-c211-442c-92cc-e5dc3ed02f94" />

- **Logs de auditoria**


  <img width="711" height="173" alt="LogAudi" src="https://github.com/user-attachments/assets/548535f6-80f0-4fd1-a4ac-b586b3871b9c" />


- **Autenticação**

  <img width="717" height="101" alt="Autenticacao" src="https://github.com/user-attachments/assets/356481ef-d44d-458a-87d6-4faa2baf8b4c" />

---
