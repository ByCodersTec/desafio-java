# Generated by Django 3.2.5 on 2021-07-30 11:58

import django.core.validators
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = []

    operations = [
        migrations.CreateModel(
            name="Transaction",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                ("value", models.IntegerField(help_text="Valor da movimentação.")),
                ("date", models.DateField(help_text="Data da ocorrência")),
                (
                    "time",
                    models.TimeField(
                        help_text="Hora da ocorrência atendendo ao fuso de UTC-3"
                    ),
                ),
                ("recipient_cpf", models.IntegerField(help_text="CPF do beneficiário")),
                (
                    "card",
                    models.IntegerField(help_text="Cartão utilizado na transação"),
                ),
                (
                    "store_owner",
                    models.CharField(
                        help_text="Nome do representante da loja", max_length=120
                    ),
                ),
                (
                    "store_name",
                    models.CharField(help_text="Nome da loja", max_length=120),
                ),
            ],
        ),
        migrations.CreateModel(
            name="TransactionType",
            fields=[
                (
                    "id",
                    models.BigAutoField(
                        auto_created=True,
                        primary_key=True,
                        serialize=False,
                        verbose_name="ID",
                    ),
                ),
                (
                    "type_id",
                    models.IntegerField(
                        validators=[
                            django.core.validators.MinValueValidator(1),
                            django.core.validators.MaxValueValidator(9),
                        ]
                    ),
                ),
                ("description", models.CharField(max_length=120)),
                (
                    "nature",
                    models.CharField(
                        choices=[("+", "Entrada"), ("-", "Saída")], max_length=120
                    ),
                ),
                (
                    "transactions",
                    models.ForeignKey(
                        on_delete=django.db.models.deletion.PROTECT,
                        related_name="type",
                        to="cnab_handler.transaction",
                    ),
                ),
            ],
        ),
    ]
