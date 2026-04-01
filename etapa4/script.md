CREATE TABLE Operador (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    nivelAcesso TEXT NOT NULL 
);

CREATE TABLE Drone (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    status TEXT NOT NULL DEFAULT 'Inativo', -- Ex: 'Disponível', 'Em Missão', 'Manutenção'
    nivelBateria REAL CHECK(nivelBateria >= 0 AND nivelBateria <= 100),
    versaoFirmware TEXT
);

CREATE TABLE Missao (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo TEXT NOT NULL,
    status TEXT NOT NULL DEFAULT 'Planejada',
    id_drone INTEGER,
    FOREIGN KEY (id_drone) REFERENCES Drone(id) ON DELETE SET NULL
);

CREATE TABLE Sensor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    tipo_sensor TEXT NOT NULL, -- 'Lidar', 'Camera', 'GPS'
    status TEXT NOT NULL DEFAULT 'Online',
    id_drone INTEGER NOT NULL,
    FOREIGN KEY (id_drone) REFERENCES Drone(id) ON DELETE CASCADE
);

CREATE TABLE Telemetria (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    localizacao TEXT NOT NULL,
    velocidade REAL NOT NULL,
    statusDrone TEXT NOT NULL,
    data_envio DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_drone INTEGER NOT NULL,
    FOREIGN KEY (id_drone) REFERENCES Drone(id) ON DELETE CASCADE
);

CREATE TABLE LogAuditoria (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data DATETIME DEFAULT CURRENT_TIMESTAMP,
    evento TEXT NOT NULL,
    id_operador INTEGER,
    id_missao INTEGER,
    FOREIGN KEY (id_operador) REFERENCES Operador(id),
    FOREIGN KEY (id_missao) REFERENCES Missao(id)
);

CREATE TABLE Autenticacao (
    id_operador INTEGER PRIMARY KEY,
    hash_biometria BLOB, -- Armazenamento seguro de dados biométricos
    segredo_mfa TEXT,
    FOREIGN KEY (id_operador) REFERENCES Operador(id) ON DELETE CASCADE
);

INSERT INTO Operador (nome, nivelAcesso) 
VALUES ('Gabriel Medina', 'Comandante Alpha');

VALUES ('Marcelo Prass', 'Operador de Campo');


INSERT INTO Autenticacao (id_operador, hash_biometria, segredo_mfa) 
VALUES (1, x'A1B2C3D4E5', 'MFA_SECRET_99');

INSERT INTO Drone (status, nivelBateria, versaoFirmware) 
VALUES ('Disponível', 95.5, 'v2.4.0-falcão');

INSERT INTO Sensor (tipo_sensor, status, id_drone) VALUES ('Lidar', 'Online', 1);
INSERT INTO Sensor (tipo_sensor, status, id_drone) VALUES ('Camera', 'Online', 1);
INSERT INTO Sensor (tipo_sensor, status, id_drone) VALUES ('GPS', 'Online', 1);

INSERT INTO Missao (tipo, status, id_drone) 
VALUES ('Reconhecimento Hostil', 'Em Execução', 1);

INSERT INTO Telemetria (localizacao, velocidade, statusDrone, id_drone) 
VALUES ('-23.5505, -46.6333', 45.2, 'Em Voo', 1);

INSERT INTO LogAuditoria (evento, id_operador, id_missao) 
VALUES ('Início de Missão Tática Autorizado', 1, 1);

INSERT INTO LogAuditoria (evento, id_operador, id_missao) 
VALUES ('Telemetria Sincronizada com Sucesso', 1, 1);
