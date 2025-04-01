CREATE TABLE schedule (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          task VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          member_name VARCHAR(255) NOT NULL,
                          created_at DATETIME NOT NULL,
                          updated_at DATETIME NOT NULL
)

CREATE TABLE member (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        email VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        updated_at TIMESTAMP NOT NULL
);