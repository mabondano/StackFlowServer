-- 1. users y roles
CREATE TABLE users (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  email VARCHAR(150) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL UNIQUE,
  description VARCHAR(255)
);

CREATE TABLE user_roles (
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (user_id) REFERENCES users(id),
  FOREIGN KEY (role_id) REFERENCES roles(id)
);

-- 2. user_settings
CREATE TABLE user_settings (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  `key` VARCHAR(100) NOT NULL,
  `value` TEXT,
  type VARCHAR(50),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 3. projects y usuarios en proyectos
CREATE TABLE projects (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  description TEXT,
  owner_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (owner_id) REFERENCES users(id)
);

CREATE TABLE project_users (
  project_id BIGINT NOT NULL,
  user_id BIGINT NOT NULL,
  PRIMARY KEY (project_id, user_id),
  FOREIGN KEY (project_id) REFERENCES projects(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 4. datasets
CREATE TABLE datasets (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  source VARCHAR(255),
  metadata JSON,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. signals y datos de señal
CREATE TABLE signals (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  project_id BIGINT,
  user_id BIGINT,
  name VARCHAR(150) NOT NULL,
  type VARCHAR(100),
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (project_id) REFERENCES projects(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE signal_data (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  signal_id BIGINT NOT NULL,
  format VARCHAR(50),
  data LONGBLOB,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (signal_id) REFERENCES signals(id)
);

-- 6. grupos de señales
CREATE TABLE signal_groups (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  name VARCHAR(150) NOT NULL,
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE signal_group_signals (
  signal_group_id BIGINT NOT NULL,
  signal_id BIGINT NOT NULL,
  PRIMARY KEY (signal_group_id, signal_id),
  FOREIGN KEY (signal_group_id) REFERENCES signal_groups(id),
  FOREIGN KEY (signal_id) REFERENCES signals(id)
);

-- 7. models y archivos de modelo
CREATE TABLE models (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  name VARCHAR(150) NOT NULL,
  type VARCHAR(100),
  description TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE model_files (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  model_id BIGINT NOT NULL,
  format VARCHAR(50),
  file LONGBLOB,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (model_id) REFERENCES models(id)
);

-- 8. pipelines, pasos y versiones
CREATE TABLE pipelines (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  name VARCHAR(150) NOT NULL,
  config JSON,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE pipeline_steps (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  pipeline_id BIGINT NOT NULL,
  step_order INT NOT NULL,
  operation VARCHAR(100),
  params JSON,
  FOREIGN KEY (pipeline_id) REFERENCES pipelines(id)
);

CREATE TABLE pipeline_versions (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  pipeline_id BIGINT NOT NULL,
  version_number INT NOT NULL,
  operation VARCHAR(50) NOT NULL,
  inputs_json TEXT NOT NULL,
  output_data LONGBLOB NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (pipeline_id) REFERENCES pipelines(id)
);

CREATE TABLE pipeline_current_version (
  pipeline_id BIGINT PRIMARY KEY,
  current_version INT NOT NULL,
  FOREIGN KEY (pipeline_id) REFERENCES pipelines(id)
);

-- 9. history
CREATE TABLE history (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  entity_type VARCHAR(100),
  entity_id BIGINT,
  action VARCHAR(50),
  description TEXT,
  date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 10. memory_slots
CREATE TABLE memory_slots (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  `key` VARCHAR(150) NOT NULL,
  `value` TEXT,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 11. config global
CREATE TABLE config (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  `key` VARCHAR(150) NOT NULL UNIQUE,
  `value` TEXT,
  type VARCHAR(50),
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 12. escenarios
CREATE TABLE scenarios (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  description TEXT,
  creator_id BIGINT NOT NULL,
  config JSON,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (creator_id) REFERENCES users(id)
);

CREATE TABLE scenario_entities (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  scenario_id BIGINT NOT NULL,
  entity_type VARCHAR(100) NOT NULL,
  entity_id BIGINT NOT NULL,
  role VARCHAR(100),
  FOREIGN KEY (scenario_id) REFERENCES scenarios(id)
);

CREATE TABLE scenario_runs (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  scenario_id BIGINT NOT NULL,
  user_id BIGINT,
  status VARCHAR(50),
  result TEXT,
  started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  finished_at TIMESTAMP,
  FOREIGN KEY (scenario_id) REFERENCES scenarios(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 13. cola de jobs y post-process
CREATE TABLE job_queue (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  type VARCHAR(100),
  entity_type VARCHAR(100),
  entity_id BIGINT,
  payload JSON,
  status VARCHAR(50),
  priority INT DEFAULT 0,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE post_process_tasks (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  job_id BIGINT NOT NULL,
  type VARCHAR(100),
  status VARCHAR(50),
  params JSON,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (job_id) REFERENCES job_queue(id)
);

-- 14. conexiones externas y schedules
CREATE TABLE db_connections (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT,
  name VARCHAR(150) NOT NULL,
  type VARCHAR(50),
  host VARCHAR(255),
  port INT,
  db_name VARCHAR(150),
  user VARCHAR(100),
  password_encrypted VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE schedules (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  job_id BIGINT NOT NULL,
  user_id BIGINT,
  description VARCHAR(255),
  cron_expression VARCHAR(100),
  status VARCHAR(50),
  last_run_at TIMESTAMP,
  next_run_at TIMESTAMP,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (job_id) REFERENCES job_queue(id),
  FOREIGN KEY (user_id) REFERENCES users(id)
);
