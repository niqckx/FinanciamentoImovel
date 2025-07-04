# Projeto Financiamento

## Visão Geral
Este projeto simula financiamentos imobiliários com cálculos de parcelas mensais e totais.  
A interação com o usuário é feita via console, com validações para garantir dados válidos.

## Estrutura de Pacotes
- **modelo:**  
  Classe `Financiamento`

- **util:**  
  Classe `InterfaceUsuario`

- **main:**  
  Classe `Main`

## Pacote modelo - Classe Financiamento
**Atributos privados:**  
- `valorImovel` (double): valor do imóvel.  
- `prazoFinanciamento` (int): prazo em anos.  
- `taxaJurosAnual` (double): taxa de juros anual em %.

**Métodos públicos:**  
- `calcularPagamentoMensal()`: calcula parcela mensal usando juros simples mensais.  
- `calcularTotalPagamento()`: calcula total pago no financiamento.  
- Getters para os atributos.  
- `exibirResumoFinanciamento()`: imprime resumo do financiamento.

## Pacote util - Classe InterfaceUsuario
- Lê dados do usuário via console.  
- Valida entradas garantindo:  
  - Valor do imóvel: positivo, aceita formato brasileiro (ex: 280.000,00).  
  - Prazo do financiamento: inteiro positivo.  
  - Taxa de juros anual: decimal positivo.  
- Usa loops para repetir entrada até que seja válida.  
- Método auxiliar para validar formato numérico.

## Pacote main - Classe Main
- Cria um objeto `InterfaceUsuario` para entrada de dados.  
- Armazena financiamentos em uma lista `ArrayList`.  
- Recebe um financiamento do usuário e cria 3 financiamentos fixos.  
- Exibe valores de cada financiamento (imóvel e total financiado).  
- Mostra soma total dos valores dos imóveis e financiamentos.

## Exemplo de execução
- Digite o valor do imóvel (ex: 280000.00): 280.000,00
- Digite o prazo do financiamento em anos: 64
- Digite a taxa de juros anual (ex: 7.5): 1.5

- Financiamento 1 – valor do imóvel: R$ 280000,00, valor do financiamento: R$ 280350,00.
- Financiamento 2 – valor do imóvel: R$ 300000,00, valor do financiamento: R$ 301875,00.
- Financiamento 3 – valor do imóvel: R$ 150000,00, valor do financiamento: R$ 150812,50.
- Financiamento 4 – valor do imóvel: R$ 250000,00, valor do financiamento: R$ 251458,33.

Total de todos os imóveis: R$ 980000,00, total de todos os financiamentos: R$ 984495,83.

## Instruções de uso
1. Compile todos os pacotes (`modelo`, `util`, `main`).  
2. Execute a classe `main.Main`.  
3. Insira os dados conforme solicitado no console.

## Considerações finais
- Projeto modular e organizado.  
- Entrada de dados validada e adaptada para formatos brasileiros.  
- Cálculo financeiro baseado em juros simples mensais.  
- Fácil expansão e manutenção.

---

**Desenvolvedor:** Nicole Oliveira Araújo  
**Contato:** nicoleoaraujoo@gmail.com
