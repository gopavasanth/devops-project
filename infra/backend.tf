terraform {
  backend "s3" {
    bucket         = "gopa-s3-bucket-xyz"
    key            = "gopa/terraform.tfstate"
    region         = "us-west-2"
    encrypt        = true
    dynamodb_table = "terraform-lock"
  }
}