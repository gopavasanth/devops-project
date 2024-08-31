provider "aws" {
    region = "us-west-2"
}

module "ec_instance" {
    source        = "./modules/ec2_instance"
    ami           = "ami-03cc8375791cb8bcf"
    instance_type = "t2.micro"
    subnet_id     = "subnet-0d3156f5608673ba5"
    key_name      = "gopa-aws"
    instance_name = "gopa-ec2-instance"
}

module "s3_bucket" {
  source = "./modules/s3_bucket"
  bucket = "gopa-s3-bucket-xyz"
}

module "aws_dynamodb_table" {
  source = "./modules/dynamodb_table"
}

module "eks_cluster" {
  source = "./modules/eks_cluster"
  kubernetes_version = 1.26
  vpc_cidr = "10.0.0.0/16"
}