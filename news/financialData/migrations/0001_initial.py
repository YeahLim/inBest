# Generated by Django 3.2.13 on 2023-10-01 10:21

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Company',
            fields=[
                ('seq', models.AutoField(primary_key=True, serialize=False)),
                ('company_code', models.CharField(max_length=10, unique=True)),
                ('company_name', models.CharField(max_length=255)),
                ('company_stock_code', models.CharField(max_length=10)),
                ('company_real_industry_code', models.CharField(max_length=10, null=True)),
                ('company_industry', models.CharField(max_length=50)),
                ('company_stock_type', models.IntegerField(default=0, null=True)),
            ],
        ),
        migrations.CreateModel(
            name='FinancialProduct',
            fields=[
                ('seq', models.BigAutoField(primary_key=True, serialize=False)),
                ('fin_prdt_nm', models.CharField(max_length=255, null=True)),
                ('kor_co_nm', models.CharField(max_length=255, null=True)),
                ('co_type_nm', models.CharField(max_length=255, null=True)),
                ('bank_type_nm', models.CharField(max_length=255, null=True)),
                ('intr_rate_type_nm', models.CharField(max_length=255, null=True)),
                ('save_trm', models.BigIntegerField(null=True)),
                ('intr_rate', models.FloatField(null=True)),
                ('intr_rate2', models.FloatField(null=True)),
                ('fin_prdt_cd', models.CharField(max_length=255, null=True)),
                ('join_way', models.CharField(max_length=255, null=True)),
                ('mtrt_int', models.CharField(max_length=255, null=True)),
                ('spcl_cnd', models.CharField(max_length=255, null=True)),
                ('join_member', models.CharField(max_length=255, null=True)),
                ('etc_note', models.CharField(max_length=255, null=True)),
                ('max_limit', models.BigIntegerField(null=True)),
                ('rsrv_type_nm', models.CharField(default=None, max_length=255, null=True)),
            ],
        ),
        migrations.CreateModel(
            name='FinancialStatement_2022',
            fields=[
                ('seq', models.AutoField(primary_key=True, serialize=False)),
                ('total_assets', models.BigIntegerField(default=None, null=True)),
                ('total_liabilities', models.BigIntegerField(default=None, null=True)),
                ('revenue', models.BigIntegerField(default=None, null=True)),
                ('operating_profit', models.BigIntegerField(default=None, null=True)),
                ('net_income', models.BigIntegerField(default=None, null=True)),
                ('company_seq', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='financial_statements_2022', to='financialData.company')),
            ],
        ),
        migrations.CreateModel(
            name='FinancialStatement_2021',
            fields=[
                ('seq', models.AutoField(primary_key=True, serialize=False)),
                ('revenue', models.BigIntegerField(default=None, null=True)),
                ('net_income', models.BigIntegerField(default=None, null=True)),
                ('company_seq', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='financial_statements_2021', to='financialData.company')),
            ],
        ),
        migrations.CreateModel(
            name='FinancialStatement',
            fields=[
                ('seq', models.AutoField(primary_key=True, serialize=False)),
                ('current_assets', models.BigIntegerField(default=None, null=True)),
                ('non_current_assets', models.BigIntegerField(default=None, null=True)),
                ('total_assets', models.BigIntegerField(default=None, null=True)),
                ('current_liabilities', models.BigIntegerField(default=None, null=True)),
                ('non_current_liabilities', models.BigIntegerField(default=None, null=True)),
                ('total_liabilities', models.BigIntegerField(default=None, null=True)),
                ('capital', models.BigIntegerField(default=None, null=True)),
                ('total_equity', models.BigIntegerField(default=None, null=True)),
                ('revenue', models.BigIntegerField(default=None, null=True)),
                ('gross_profit', models.BigIntegerField(default=None, null=True)),
                ('operating_profit', models.BigIntegerField(default=None, null=True)),
                ('income_before_tax', models.BigIntegerField(default=None, null=True)),
                ('income_tax_expense', models.BigIntegerField(default=None, null=True)),
                ('net_income', models.BigIntegerField(default=None, null=True)),
                ('total_asset_growth_rate', models.FloatField(default=None, null=True)),
                ('revenue_asset_growth_rate', models.FloatField(default=None, null=True)),
                ('net_income_growth_rate', models.FloatField(default=None, null=True)),
                ('operating_profit_margin', models.FloatField(default=None, null=True)),
                ('roe', models.FloatField(default=None, null=True)),
                ('roic', models.FloatField(default=None, null=True)),
                ('debt_to_equity_ratio', models.FloatField(default=None, null=True)),
                ('company_seq', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='financial_statements', to='financialData.company')),
            ],
        ),
        migrations.CreateModel(
            name='CompanyIndicatorsScore',
            fields=[
                ('seq', models.AutoField(primary_key=True, serialize=False)),
                ('stability', models.IntegerField(default=None, null=True)),
                ('size', models.IntegerField(default=None, null=True)),
                ('growth', models.IntegerField(default=None, null=True)),
                ('profitability', models.IntegerField(default=None, null=True)),
                ('revenue_growth', models.FloatField(default=None, null=True)),
                ('operating_profit_growth', models.FloatField(default=None, null=True)),
                ('company_seq', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='CompanyIndicatorsScore', to='financialData.company')),
            ],
        ),
        migrations.CreateModel(
            name='CompanyIndicators',
            fields=[
                ('seq', models.AutoField(primary_key=True, serialize=False)),
                ('stability', models.IntegerField(default=None, null=True)),
                ('size', models.IntegerField(default=None, null=True)),
                ('growth', models.IntegerField(default=None, null=True)),
                ('profitability', models.IntegerField(default=None, null=True)),
                ('revenue_growth', models.FloatField(default=None, null=True)),
                ('operating_profit_growth', models.FloatField(default=None, null=True)),
                ('company_seq', models.ForeignKey(on_delete=django.db.models.deletion.CASCADE, related_name='CompanyIndicators', to='financialData.company')),
            ],
        ),
    ]
