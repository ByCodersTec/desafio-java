# Generated by Django 3.2.7 on 2021-09-07 00:22

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('core', '0002_auto_20210907_0022'),
    ]

    operations = [
        migrations.AlterField(
            model_name='cnab',
            name='cpf',
            field=models.IntegerField(blank=True, null=True),
        ),
    ]