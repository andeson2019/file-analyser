# File Analyzer

## Aplicação desenvolvida como solução para teste de desenvolvedor.

O software desenvolvido tem como finalidade realizar a análise de dados
contidos em arquivos de texto e exportar relatórios.
Os dados são lidos a partir do diretório "**_%HOMEPATH%/data/in_**" e os relatórios
são exportados para o diretório "**_%HOMEPATH%/data/out_**". Ambos os diretórios são criados automaticamente 
ao iniciar a aplicação. A variável "**_%HOMEPATH%_**" corresponde ao diretório padrão do usuário logado.

A aplicação tem como objetivo realizar a leitura de arquivos contendo dados de clientes, vendedores e vendas.
Após a leitura dos arquivos de dados, deve exportar um relatório com as seguintes informações:

* Quantidade de clientes;
* Quantidade de vendedores;
* ID da venda mais cara;
* Nome do vendedor da pior venda.

## Requisitos

- Java versão 11 ou superior;
- Maven;
- Git;
- OS Linux ou Windows.
     

## Instalação

- Basta fazer o download do projeto ou clonar usando o comando:
```
git clone git@github.com:andeson2019/file-analyser.git 
```   
## Execução

- Após o download ou clonagem do projeto, o mesmo pode ser importado para 
uma IDE com suporte ao gerenciador de dependências Maven ou ser executado utilizando a ferramenta de build do próprio Maven.
- Não é necessário nenhuma configuração adicional.
- Os diretórios de entrada e saída de dados são criados automaticamente pela aplicação.
- Se os diretórios já existirem, basta criar ou colar os arquivos de entrada e a aplicação
realizará a leitura de forma automática. Caso já existam arquivos no diretório de entrada, a aplicação realizará 
a leitura ao iniciar.
- A aplicação faz a leitura apenas de arquivos com extensão "**.dat**".

## Formato de Arquivo
##### A aplicação lê apenas arquivos com a extensão "**.dat**" com o dados formatados de acordo com o exemplo a seguir:


```
001ç1234567891234çPedroç50000
001ç3245678865434çPauloç40000.99
002ç2345675434544345çJose da SilvaçRural
002ç2345675433444345çEduardo PereiraçRural
003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çPedro
003ç08ç[1-34-10,2-33-1.50,3-40-0.10]çPaulo
``` 
 



