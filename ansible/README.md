# Ansible Automation

This project contains Ansible playbooks and related files for configuring and managing infrastructure for various components.

## Components

- **Java**: Install Java version 11.
- **Jenkins Agent**: Setup and configuration of the jenkins agent.
- **Docker**: Installation of the container runtime.
- **kubectl**: Installation of the Kubernetes command-line tool.
- **aws**: Installation of the aws command-line tool.
- **Jenkins Agent**: Installation on the EC2 instance.

## Directory Structure

- **`ansible/`**: Contains Ansible playbooks, roles, and inventory files.
  - **`playbooks/`**: Directory for Ansible playbooks.
    - `playbook.yml`: Main playbook file.
  - **`roles/`**: Directory for Ansible roles.
  - **`inventory/`**: Directory for inventory files.
    - `inventory.ini`: Inventory file listing the hosts.

## Prerequisites

- **Ansible**: Ensure Ansible is installed on your machine. You can install it using pip:
  ```bash
  pip install ansible

## How to Execute

Run the following command to execute the Ansible playbook:
  ```bash
  ansible-playbook -i inventory.ini playbook.yml --private-key=../gopa-jenkins.pem
  ```

