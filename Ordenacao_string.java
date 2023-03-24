import java.util.*;
import java.io.*;

public class Main {
    /*
     * Descricao: função que imprime a string ordenada
     * Parametro: uma string - a ser ordenada
     */
    public static void printNomes(String[] str) {
        for (String s : str) {
            System.out.println(s);
        }
        System.out.println("\n");
    }

    /*
     * Descricao: função que troca dois elementos de uma string
     * Parametro: uma string - a ser ordenada - e dois inteiros - indices da string
     */
    public static void swap(String[] str, int i, int j) {
        String aux = str[i];
        str[i] = str[j];
        str[j] = aux;
    }

    /*
     * Descricao: função que ordena uma string através o método bubblesort de forma crescente
     * Parametro: uma string - a ser ordenada
     */
    public static void bubblesortCres(String[] str) {
        for (int i = str.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (str[j].toUpperCase().compareTo(str[j + 1].toUpperCase()) > 0) {
                    swap(str, j, j + 1);
                }
            }
        }
        printNomes(str);
    }

    /*
     * Descricao: função que ordena uma string através o método bubblesort de forma decrescente
     * Parametro: uma string - a ser ordenada
     */
    public static void bubblesortDecres(String[] str) {
        for (int i = str.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (str[j].toUpperCase().compareTo(str[j + 1].toUpperCase()) <= 0) {
                    swap(str, j, j + 1);
                }
            }
        }
        printNomes(str);
    }

    /*
     * Descricao: função que ordena uma string de forma crescente pelo metodo selectionsort
     * Parametro: uma string - a ser ordenada
     */
    public static void selectionsortCres(String[] str) {
        for (int i = 0; i < 12; i++) {
            int menor = i;

            for (int j = i + 1; j < str.length; j++) {
                if (str[menor].toUpperCase().compareTo(str[j].toUpperCase()) > 0) {
                    menor = j;
                }
            }
            if (i != menor) {
                swap(str, i, menor);
            }
        }
    }

    /*
     * Descricao: função que ordena uma string de forma decrescente pelo metodo selectionsort
     * Parametro: uma string - a ser ordenada
     */
    public static void selectionsortDecres(String[] str) {
        for (int i = 0; i < 12; i++) {
            int menor = i;


            for (int j = i + 1; j < str.length; j++) {
                if (str[menor].toUpperCase().compareTo(str[j].toUpperCase()) <= 0) {
                    menor = j;
                }
            }
            if (i != menor) {
                swap(str, i, menor);
            }
        }
    }

    /*
     * Descricao: função que ordena uma string através o método insertionsort de forma crescente
     * Parametro: uma string - a ser ordenada
     */
    public static void insertionsortCres(String[] str) {
        for (int i = 1; i < str.length; i++) {
            String temp = str[i];
            int j = i - 1;

            while (j >= 0 && str[j].toUpperCase().compareTo(temp.toUpperCase()) > 0) {
                str[j + 1] = str[j];
                j--;
            }
            str[j + 1] = temp;
        }
    }

    /*
     * Descricao: função que ordena uma string através o método insertionsort de forma decrescente
     * Parametro: uma string - a ser ordenada
     */
    public static void insertionsortDecres(String[] str) {
        for (int i = 1; i < str.length; i++) {
            String temp = str[i];
            int j = i - 1;

            while (j >= 0 && str[j].toUpperCase().compareTo(temp.toUpperCase()) <= 0) {
                str[j + 1] = str[j];
                j--;
            }
            str[j + 1] = temp;
        }
    }

    /*
     * Descricao:função que calcula o indice do elemento pai no heap
     * Parametro: um inteiro - indice do filho
     * Retorno: um inteiro - indice do pai
     */
    public static int indice_pai(int filho) {
        int flag = filho;

        filho /= 2;

        if (flag % 2 == 0) {
            filho--;
        }
        return filho;
    }

    /*
     * Descricao: função que constroi o heap de uma string

     * Parametros: uma string - a ser construida e um inteiro - tamanho da string
     */
    public static void construirCres(String[] str, int tam) {
        int indice = indice_pai(tam);
        int i = tam;

        while (i > 0 && str[i].toUpperCase().compareTo(str[indice].toUpperCase()) > 0) {
            swap(str, i, indice);

            i = indice_pai(i);

            if (indice % 2 == 0) {
                indice = indice_pai(indice);
            } else {
                indice /= 2;
            }
        }
    }

    /*
     * Descricao: função que reconstroi uma string
     * Parametros: string - a ser ordenada e um inteiro - tamanho da string
     */
    public static void reconstruirCres(String[] str, int tam) {
        int filho;
        int meio = indice_pai(tam);
        int i = 0;

        //Verifica se ainda possui filhos
        while (i <= meio) {
            //Seleciona o maior filho
            if (2 * i + 1 == tam || str[2 * i + 1].toUpperCase().compareTo(str[2 * i + 2].toUpperCase()) > 0) {
                filho = 2 * i + 1;
            } else {
                filho = 2 * i + 2;
            }

            //reconstroi o vetor
            if (str[i].toUpperCase().compareTo(str[filho].toUpperCase()) < 0) {
                swap(str, i, filho);
                i = filho;
            } else {
                i = tam;
            }
        }
    }

    /*
     * Descricao: função que ordena uma string por meio do heapsort de forma crescente
     * Parametro: uma string - a ser ordenado
     */
    public static void heapsortCres(String[] str) {
        int tam;
        //Construcao do heap
        for (tam = 1; tam < str.length; tam++) {
            construirCres(str, tam);
        }
        //Ordenacao do vetor
        tam = str.length - 1;
        while (tam > 0) {
            swap(str, 0, tam--);
            reconstruirCres(str, tam);
        }
    }

    /*
     * Descricao: função que constroi o heap de uma string

     * Parametros: uma string - a ser construida e um inteiro - tamanho da string
     */
    public static void construirDecres(String[] str, int tam) {
        int indice = indice_pai(tam);
        int i = tam;

        while (i > 0 && str[i].toUpperCase().compareTo(str[indice].toUpperCase()) <= 0) {
            swap(str, i, indice);

            i = indice_pai(i);

            if (indice % 2 == 0) {
                indice = indice_pai(indice);
            } else {
                indice /= 2;
            }
        }
    }

    /*
     * Descricao: função que reconstroi uma string
     * Parametros: string - a ser ordenada e um inteiro - tamanho da string
     */
    public static void reconstruirDecres(String[] str, int tam) {
        int filho;
        int meio = indice_pai(tam);
        int i = 0;

        //Verifica se ainda possui filhos
        while (i <= meio) {
            //Seleciona o maior filho
            if (2 * i + 1 == tam || str[2 * i + 1].toUpperCase().compareTo(str[2 * i + 2].toUpperCase()) <= 0) {
                filho = 2 * i + 1;
            } else {
                filho = 2 * i + 2;
            }

            //reconstroi o vetor
            if (str[i].toUpperCase().compareTo(str[filho].toUpperCase()) > 0) {
                swap(str, i, filho);
                i = filho;
            } else {
                i = tam;
            }
        }
    }

    /*
     * Descricao: função que ordena uma string por meio do heapsort de forma decrescente
     * Parametro: uma string - a ser ordenado
     */
    public static void heapsortDecres(String[] str) {
        int tam;
        //Construcao do heap
        for (tam = 1; tam < str.length; tam++) {
            construirDecres(str, tam);
        }
        //Ordenacao do vetor
        tam = str.length - 1;
        while (tam > 0) {
            swap(str, 0, tam--);
            reconstruirDecres(str, tam);
        }
    }

    /*
     * Descricao: função que divide a string em duas strings e intercala eles entre si
     * Parametros: uma string - a ser intercalado e 3 inteiros - esquerda, direita e meio
     */
    public static void intercalarCres(String[] str, int esq, int meio, int dir) {
        int nEsq = (meio - esq) + 1;
        int nDir = dir - meio;

        String[] strEsq = new String[nEsq + 1];
        String[] strDir = new String[nDir + 1];

        //Sentinela no final dos dois arrays
        strEsq[nEsq] = "ZZZZZZZZZZZZZZ";
        strDir[nDir] = "ZZZZZZZZZZZZZZ";

        int i, j, k;

        for (i = 0; i < nEsq; i++) {
            strEsq[i] = str[esq + i];
        }

        for (j = 0; j < nDir; j++) {
            strDir[j] = str[(meio + 1) + j];
        }

        for (i = 0,  j = 0, k = esq; k <= dir; k++) {
            if (strEsq[i].toUpperCase().compareTo(strDir[j].toUpperCase()) <= 0) {
                str[k] = strEsq[i];
                i++;
            } else {
                str[k] = strDir[j];
                j++;
            }
        }
    }

    /*
     * Descricao: função que ordena uma string de forma crescente por meio do MergeSort
     * Parametro: uma string - a ser ordenado e dois inteiros - esquerda e direita
     */
    public static void mergesortCres(String[] str, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesortCres(str, esq, meio);
            mergesortCres(str, meio + 1, dir);
            intercalarCres(str, esq, meio, dir);
        }
    }

    /*
     * Descricao: função que divide a string em duas strings e intercala eles entre si
     * Parametros: uma string - a ser intercalado e 3 inteiros - esquerda, direita e meio
     */
    public static void intercalarDecres(String[] str, int esq, int meio, int dir) {
        int nEsq = (meio - esq) + 1;
        int nDir = dir - meio;

        String[] strEsq = new String[nEsq + 1];
        String[] strDir = new String[nDir + 1];

        //Sentinela no final dos dois arrays
        strEsq[nEsq] = "ZZZZZZZZZZZZZZ";
        strDir[nDir] = "ZZZZZZZZZZZZZZ";

        //i = index da esquerda, j = index da direita, k = index geral
        int i, j, k;

        for (i = 0; i < nEsq; i++) {
            strEsq[i] = str[esq + i];
        }

        for (j = 0; j < nDir; j++) {
            strDir[j] = str[(meio + 1) + j];
        }

        for (i = 0,  j = 0, k = esq; k <= dir; k++) {
            if (strEsq[i].toUpperCase().compareTo(strDir[j].toUpperCase()) >= 0) {
                str[k] = strEsq[i];
                i++;
            } else {
                str[k] = strDir[j];
                j++;
            }
        }
    }

    /*
     * Descricao: função que ordena uma string de forma crescente por meio do MergeSort
     * Parametro: uma string - a ser ordenada e dois inteiros - esquerda e direita
     */
    public static void mergesortDecres(String[] str, int esq, int dir) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergesortDecres(str, esq, meio);
            mergesortDecres(str, meio + 1, dir);
            intercalarDecres(str, esq, meio, dir);
        }

    }

    /*
     * Descricao: função que ordena uma string de forma crescente por meio do QuickSort
     * Parametro: uma string - a ser ordenada e dois inteiros - esquerda e direita
     */
    public static void quicksortCres (String[] str, int esq, int dir) {
        int i, j, posicao;
        String pivo;

        i = esq;
        j = dir;
        posicao = (esq + dir) / 2;
        pivo = new String (str[posicao]);

        while (i <= j) {
            while (str[i].toUpperCase().compareTo(pivo.toUpperCase()) < 0) {
                i ++;
            }

            while (str[j].toUpperCase().compareTo(pivo.toUpperCase()) > 0) {
                j --;
            }

            if (i<=j) {
                swap(str, i, j);
                i++;
                j--;
            }
        }

        if (esq < j) {
            quicksortCres(str, esq, j);
        }

        if (i < dir) {
            quicksortCres(str, i, dir);
        }
    }

    /*
     * Descricao: função que ordena uma string de forma decrescente por meio do QuickSort
     * Parametro: uma string - a ser ordenada e dois inteiros - esquerda e direita
     */
    public static void quicksortDecres (String[] str, int esq, int dir) {
        int i, j, posicao;
        String pivo;

        i = esq;
        j = dir;
        posicao = (esq + dir) / 2;
        pivo = new String (str[posicao]);

        while (i <= j) {
            while (str[i].toUpperCase().compareTo(pivo.toUpperCase()) > 0) {
                i ++;
            }

            while (str[j].toUpperCase().compareTo(pivo.toUpperCase()) < 0) {
                j --;
            }

            if (i<=j) {
                swap(str, i, j);
                i++;
                j--;
            }
        }

        if (esq < j) {
            quicksortDecres(str, esq, j);
        }

        if (i < dir) {
            quicksortDecres(str, i, dir);
        }
    }


    //código main
    public static void main(final String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File arq = new File("C:\\Users\\biasa\\Desktop\\Trabalho Pratico\\src\\nomes.txt");
        // le o arquivo de letra em letra
        FileReader new_arq = new FileReader(arq);

        BufferedReader leitor = new BufferedReader(new_arq);
        String linha = leitor.readLine();

        //variáveis
        int opcao;
        int cont = 0;
        String aux;
        String[] nomes = new String[13];
        String[] copia_nomes = new String[13];

        while (linha != null) {
            aux = linha;
            nomes[cont] = aux;
            linha = leitor.readLine();
            cont++;
        }
        leitor.close();
        new_arq.close();

        new_arq = new FileReader(arq);
        leitor = new BufferedReader(new_arq);
        linha = leitor.readLine();

        //criar o vetor copia
        copia_nomes = nomes;

        do {
            System.out.println("[1] Ordenação BubbleSort");
            System.out.println("[2] Ordenação SelectionSort");
            System.out.println("[3] Ordenação InsertionSort");
            System.out.println("[4] Ordenação HeapSort");
            System.out.println("[5] Ordenação MergeSort");
            System.out.println("[6] Ordenação QuickSort");
            System.out.println("[7] Sair");
            System.out.println("Digite uma opção: ");
            opcao = sc.nextInt();
            System.out.println("\n");

            switch (opcao) {
                case 1: {
                    int escolha = 0;
                    do {
                        System.out.println("[1] Ordenação BubbleSort Crecente");
                        System.out.println("[2] Ordenação BubbleSort Decrescente");
                        System.out.println("[3] Voltar ao menu principal");
                        escolha = sc.nextInt();
                        System.out.println("\n");

                        switch (escolha) {
                            case 1:
                                bubblesortCres(copia_nomes);
                                break;
                            case 2:
                                bubblesortDecres(copia_nomes);
                                break;

                        }
                    } while (escolha <= 2);
                }
                case 2: {
                    int escolha;
                    do {
                        System.out.println("[1] Ordenação SelectionSort Crecente");
                        System.out.println("[2] Ordenação SelectionSort Decrescente");
                        System.out.println("[3] Voltar ao menu principal");
                        escolha = sc.nextInt();
                        System.out.println("\n");
                        switch (escolha) {
                            case 1: {
                                selectionsortCres(copia_nomes);
                                printNomes(copia_nomes);
                                break;
                            }
                            case 2: {
                                selectionsortDecres(copia_nomes);
                                printNomes(copia_nomes);
                                break;
                            }
                        }
                    } while (escolha <= 2);
                }
                case 3: {
                    int escolha;
                    do {
                        System.out.println("[1] Ordenação InsertionSort Crecente");
                        System.out.println("[2] Ordenação InsertionSort Decrescente");
                        System.out.println("[3] Voltar ao menu principal");
                        escolha = sc.nextInt();
                        System.out.println("\n");
                        switch (escolha) {
                            case 1: {
                                insertionsortCres(copia_nomes);
                                printNomes(copia_nomes);
                                break;
                            }
                            case 2: {
                                insertionsortDecres(copia_nomes);
                                printNomes(copia_nomes);
                                break;
                            }
                        }
                    } while (escolha <= 2);
                }
                case 4: {
                    int escolha;
                    do {
                        System.out.println("[1] Ordenação HeapSort Crecente");
                        System.out.println("[2] Ordenação HeapSort Decrescente");
                        System.out.println("[3] Voltar ao menu principal");
                        escolha = sc.nextInt();
                        System.out.println("\n");
                        switch (escolha) {
                            case 1: {
                                heapsortCres(copia_nomes);
                                printNomes(copia_nomes);
                                break;
                            }
                            case 2: {
                                heapsortDecres(copia_nomes);
                                printNomes(copia_nomes);
                                break;
                            }
                        }
                    } while (escolha <= 2);
                }
                case 5: {
                    int escolha;
                    do {
                        System.out.println("[1] Ordenação MergeSort Crecente");
                        System.out.println("[2] Ordenação MergeSort Decrescente");
                        System.out.println("[3] Voltar ao menu principal");
                        escolha = sc.nextInt();
                        System.out.println("\n");
                        switch (escolha) {
                            case 1: {
                                mergesortCres(copia_nomes, 0, copia_nomes.length - 1);
                                printNomes(copia_nomes);
                                break;
                            }
                            case 2: {
                                mergesortDecres(copia_nomes, 0, copia_nomes.length - 1);
                                printNomes(copia_nomes);
                                break;
                            }
                        }
                    } while (escolha <= 2);
                }
                case 6: {
                    int escolha;
                    do {
                        System.out.println("[1] Ordenação QuickSort Crecente");
                        System.out.println("[2] Ordenação QuickSort Decrescente");
                        System.out.println("[3] Voltar ao menu principal");
                        escolha = sc.nextInt();
                        System.out.println("\n");
                        switch (escolha) {
                            case 1: {
                                quicksortCres(copia_nomes, 0, copia_nomes.length-1);
                                printNomes(copia_nomes);
                                break;
                            }
                            case 2: {
                                quicksortDecres(copia_nomes, 0, copia_nomes.length-1);
                                printNomes(copia_nomes);
                                break;
                            }
                        }
                    } while (escolha <= 2);
                }
                case 7: {
                    System.out.println("Até logo!");
                    break;
                }
                default: {
                    System.out.println("Opção invalida, digite outra opção");
                    break;
                }
            }
        } while (opcao != 7);
    }

}