variable "region" {
  description = "The AWS region to deploy the resources"
  type        = string
  default     = "eu-west-1"
}

variable "ami" {
  description = "The AMI ID to use for the EC2 instance"
  type        = string
}

variable "instance_type" {
  description = "The type of instance to use"
  type        = string
  default     = "t2.micro"
}

variable "subnet_id" {
  description = "The subnet ID where the instance will be launched"
  type        = string
}

variable "key_name" {
  description = "The name of the key pair to use for the instance"
  type        = string
}

variable "instance_name" {
  description = "The name tag for the instance"
  type        = string
}
