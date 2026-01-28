-- Create a custom ENUM type for roles
CREATE TYPE user_role AS ENUM ('admin', 'customer', 'seller');
CREATE TYPE type_service AS ENUM('video', 'music', 'iptv');
CREATE TYPE state_service AS ENUM('pending', 'canceled', 'completed', 'expired')


-- Create the role table
CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name user_role UNIQUE NOT NULL,
    description TEXT
);

-- Insert the initial roles
INSERT INTO role (name, description) VALUES
('admin', 'Administrator with full access'),
('customer', 'Customer who can browse and purchase products'),
('seller', 'Seller who can manage their own products');

-- Create the users table
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL, -- Increased length for hashed passwords
    phone_number VARCHAR(100), -- Typically phone numbers are not NOT NULL
    is_active BOOLEAN DEFAULT TRUE,
    role_id INT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_role FOREIGN KEY(role_id) REFERENCES role(id) ON DELETE RESTRICT
);

-- Create a function to automatically update the updated_at timestamp
CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to call the function before any update on the users table
CREATE TRIGGER set_timestamp
BEFORE UPDATE ON users
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- =================================================================
-- E-commerce Tables for Digital Goods
-- =================================================================

-- Create the services table
CREATE TABLE services (
  id SERIAL PRIMARY KEY,
  service_name VARCHAR(100) UNIQUE NOT NULL,
  type type_service NOT NULL,
  description TEXT,
  stock INT NOT NULL CHECK (stock >= 0),
  price DECIMAL(10, 2) NOT NULL,
  created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- Add updated_at trigger for services
CREATE TRIGGER set_timestamp_services
BEFORE UPDATE ON services
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- Create the orders table
CREATE TABLE orders (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  status state_service NOT NULL DEFAULT 'pending',
  total_amount DECIMAL(10, 2) NOT NULL,
  created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_order_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE RESTRICT
);

-- Add updated_at trigger for orders
CREATE TRIGGER set_timestamp_orders
BEFORE UPDATE ON orders
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- Create the order_items table to link services to orders
CREATE TABLE order_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT NOT NULL,
    service_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 1,
    price_at_purchase DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_item_order FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE, -- If order is deleted, its items are deleted
    CONSTRAINT fk_item_service FOREIGN KEY(service_id) REFERENCES services(id) ON DELETE RESTRICT
);

-- Renamed detail_services to subscriptions for clarity
CREATE TABLE subscriptions (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT NOT NULL,
  order_item_id BIGINT UNIQUE NOT NULL, -- Each order item results in one subscription
  service_id INT NOT NULL,
  start_date TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  end_date TIMESTAMPTZ NOT NULL,
  is_active BOOLEAN DEFAULT TRUE,
  profile_name VARCHAR(100),
  profile_pin VARCHAR(20),
  account_email VARCHAR(255),
  account_password VARCHAR(255), -- Should be encrypted in the application
  created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_subscription_user FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE RESTRICT,
  CONSTRAINT fk_subscription_order_item FOREIGN KEY(order_item_id) REFERENCES order_items(id) ON DELETE RESTRICT,
  CONSTRAINT fk_subscription_service FOREIGN KEY(service_id) REFERENCES services(id) ON DELETE RESTRICT
);

-- Add updated_at trigger for subscriptions
CREATE TRIGGER set_timestamp_subscriptions
BEFORE UPDATE ON subscriptions
FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();

-- === INDEXES FOR PERFORMANCE ===
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_services_name ON services(service_name);
CREATE INDEX idx_orders_user_id ON orders(user_id);
CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_order_items_service_id ON order_items(service_id);
CREATE INDEX idx_subscriptions_user_id ON subscriptions(user_id);
CREATE INDEX idx_subscriptions_end_date ON subscriptions(end_date);
