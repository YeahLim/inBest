# Generated by Django 3.2.13 on 2023-10-03 07:20

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('financialData', '0003_company_company_image_url'),
    ]

    operations = [
        migrations.AlterField(
            model_name='company',
            name='company_image_url',
            field=models.CharField(default=None, max_length=1255, null=True),
        ),
    ]
