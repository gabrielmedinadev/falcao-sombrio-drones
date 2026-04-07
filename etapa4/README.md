# Etapa 4 – Integração de Modelos (Classes + Banco de Dados)

Esta etapa consolida a transição do **modelo de classes UML** para o **modelo relacional em nuvem**, utilizando o **Supabase (PostgreSQL)** como infraestrutura de persistência de dados.

---

## 📌 Estrutura do Banco (Supabase / PostgreSQL)

O banco de dados foi estruturado de forma a refletir 1:1 cada classe definida no diagrama UML anterior.

### Tabelas Implementadas:
- **Operadores:** Registro de usuários autorizados e seus níveis de acesso.
- **Drones:** Gestão da frota, status operacional, níveis de bateria e versão de firmware.
- **Missões:** Planejamento e histórico de execuções táticas.
- **Sensores:** Abstração para componentes de hardware (Lidar, Camera, GPS) vinculados a cada drone.
- **Telemetria:** Registro de alta frequência para localização, velocidade e status em tempo real.
- **Logs de Auditoria:** Histórico imutável de eventos para conformidade e segurança.
- **Autenticação:** Gestão de segurança biométrica e MFA (Multifator).
- **Infraestrutura de Controle:** Gestão da redundância entre Servidores Principais e de Backup (**Failover**).

---

## 🛡️ Restrições e Segurança

A integração com o **Supabase** permitiu a aplicação de restrições avançadas que elevam o nível de segurança do sistema:

1. **Row Level Security (RLS):** Implementação de políticas de segurança na tabela `logs_auditoria`, tornando os registros **imutáveis** (bloqueio total de `UPDATE` e `DELETE`).
2. **Integridade Referencial:** Uso rigoroso de **Chaves Estrangeiras (FK)** com `ON DELETE CASCADE`, garantindo a consistência entre drones e seus respectivos sensores e telemetria.
3. **Validações de Domínio (CHECK):** Atributos críticos como `nivel_bateria` (0-100), `status` e `perfil_acesso` possuem restrições nativas no banco para evitar dados inconsistentes.

![database](./database.jpg)

