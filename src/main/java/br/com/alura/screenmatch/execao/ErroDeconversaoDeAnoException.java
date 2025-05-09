package br.com.alura.screenmatch.execao;

public class ErroDeconversaoDeAnoException extends RuntimeException{
    private String mensagem;

    public ErroDeconversaoDeAnoException(String mensagem) {
        this.mensagem = mensagem;
    }
    public String getMensagem() {
        return this.mensagem;
    }

    }

