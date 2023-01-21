package com.lulu.jogodavelha

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class MainActivity : AppCompatActivity() {
    private lateinit var listaBotoes: List<AppCompatButton>

    private var listaAtivo = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    private var listaJogador1 = mutableListOf<Int>()
    private var listaJogador2 = mutableListOf<Int>()
    private var jogador = false
    private var textoJogador = "O"
    private var isEnded = false
    private lateinit var toast: Toast

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaBotoes = listOf<AppCompatButton>(
            findViewById(R.id.botao0),
            findViewById(R.id.botao1),
            findViewById(R.id.botao2),
            findViewById(R.id.botao3),
            findViewById(R.id.botao4),
            findViewById(R.id.botao5),
            findViewById(R.id.botao6),
            findViewById(R.id.botao7),
            findViewById(R.id.botao8)
        )

        for (i in listaBotoes.indices) {
            listaBotoes[i].setOnClickListener {
                conferirBotao(i)
            }
        }
        val botaoResetar = findViewById<AppCompatButton>(R.id.botao_reset)
        botaoResetar.setOnClickListener {
            jogador = false
            textoJogador = "O"
            for (i in listaBotoes.indices) {
                listaBotoes[i].text = ""
            }
            for (i in listaAtivo.indices) {
                listaAtivo[i] = 0
            }
            listaJogador1.clear()
            listaJogador2.clear()
            isEnded = false
        }
    }

    private fun conferirJogador() {
        if (jogador) {
            jogador = false
            textoJogador = "O"
        } else {
            jogador = true
            textoJogador = "X"
        }
    }

    private fun conferirBotao(i: Int) {
        if (!isEnded) {
            if (listaAtivo[i] != 0) {
                Toast.makeText(this, "A casa já foi escolhida", Toast.LENGTH_SHORT).show()
            } else {
                conferirJogador()
                if (jogador) {
                    listaAtivo[i] = 1
                    listaJogador1.add(i)
                } else {
                    listaAtivo[i] = 2
                    listaJogador2.add(i)
                }
                listaBotoes[i].text = textoJogador
                conferirEstado()
            }
        }
    }

    private fun conferirEstado() {

        if ((listaJogador1.contains(0) && listaJogador1.contains(1) && listaJogador1.contains(2)) ||
            (listaJogador1.contains(3) && listaJogador1.contains(4) && listaJogador1.contains(5)) ||
            (listaJogador1.contains(6) && listaJogador1.contains(7) && listaJogador1.contains(8)) ||
            (listaJogador1.contains(0) && listaJogador1.contains(3) && listaJogador1.contains(6)) ||
            (listaJogador1.contains(1) && listaJogador1.contains(4) && listaJogador1.contains(7)) ||
            (listaJogador1.contains(2) && listaJogador1.contains(5) && listaJogador1.contains(8)) ||
            (listaJogador1.contains(0) && listaJogador1.contains(4) && listaJogador1.contains(8)) ||
            (listaJogador1.contains(2) && listaJogador1.contains(4) && listaJogador1.contains(6))
        ) {
            Toast.makeText(this, "Jogador 1 venceu", Toast.LENGTH_SHORT).show()
            isEnded = true
        } else if ((listaJogador2.contains(0) && listaJogador2.contains(1) && listaJogador2.contains(
                2
            )) ||
            (listaJogador2.contains(3) && listaJogador2.contains(4) && listaJogador2.contains(5)) ||
            (listaJogador2.contains(6) && listaJogador2.contains(7) && listaJogador2.contains(8)) ||
            (listaJogador2.contains(0) && listaJogador2.contains(3) && listaJogador2.contains(6)) ||
            (listaJogador2.contains(1) && listaJogador2.contains(4) && listaJogador2.contains(7)) ||
            (listaJogador2.contains(2) && listaJogador2.contains(5) && listaJogador2.contains(8)) ||
            (listaJogador2.contains(0) && listaJogador2.contains(4) && listaJogador2.contains(8)) ||
            (listaJogador2.contains(2) && listaJogador2.contains(4) && listaJogador2.contains(6))
        ) {
            Toast.makeText(this, "Jogador 2 venceu", Toast.LENGTH_SHORT).show()
            isEnded = true
        } else if (!listaAtivo.contains(0)) {
            Toast.makeText(this, "Empatou", Toast.LENGTH_SHORT).show()
            isEnded = true
        }
    }

}