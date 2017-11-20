package com.sabbirrifat.tictactoe

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity() : AppCompatActivity() {

    lateinit var bacmusic:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bacmusic = MediaPlayer.create(this, R.raw.bacmu)
        bacmusic.start()

    }



    fun buClick(view: View) {

        val mediaplayer = MediaPlayer.create(this, R.raw.buclicksu)
        mediaplayer.start()

        val buselected = view as Button
        var cellId = 0
        when (buselected.id) {

            R.id.bu1 -> cellId = 1
            R.id.bu2 -> cellId = 2
            R.id.bu3 -> cellId = 3
            R.id.bu4 -> cellId = 4
            R.id.bu5 -> cellId = 5
            R.id.bu6 -> cellId = 6
            R.id.bu7 -> cellId = 7
            R.id.bu8 -> cellId = 8
            R.id.bu9 -> cellId = 9


        }


        game(cellId, buselected)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer = 1
    var over = 0

    fun emcell(buselected: Button) {
        buselected.isEnabled = false
    }

    fun game(cellId: Int, buselected: Button) {


        if (activePlayer == 1) {

            buselected.text = "X"
            player1.add(cellId)
            winner()
            activePlayer = 2
            if (over != 4) {
                autoplay()
            }

        } else {
            buselected.text = "O"
            player2.add(cellId)
            winner()
            activePlayer = 1
        }

        buselected.isEnabled = false

    }

    fun winner() {
        var winner = -1
        //row1
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        }
        //row1fin


        //row2
        if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        }
        //row2fin


        //row3
        if (player1.contains(7) && player1.contains(8) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9)) {
            winner = 2
        }
        //row3fin


        //col1
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        }
        //col11fin

        //col2
        if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        }
        //col12fin


        //col1
        if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }
        //col11fin


        //korno1
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        }
        //korno1fin

        //korno2
        if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        }
        if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }
        //korno2fin

        //draw
        var emptyCell = ArrayList<Int>()

        for (cellId in 1..9) {
            if (!(player1.contains(cellId) || player2.contains(cellId))) {
                emptyCell.add(cellId)
            }
        }
        if (emptyCell.size == 0 && winner == -1) {
            Toast.makeText(this, "Match is Drawn", Toast.LENGTH_LONG).show()
            bacmusic.stop()
            val drawn = MediaPlayer.create(this,R.raw.match_drawn_effect)
            drawn.start()
            over = 4

        }
        //draw


        if (winner != -1) {
            if (winner == 1) {
                Toast.makeText(this, "You won the game", Toast.LENGTH_LONG).show()
                bacmusic.stop()
                val winnermu = MediaPlayer.create(this,R.raw.winner_sound_effect)
                winnermu.start()

                over = 4

                var buselect: Button
                for (cellId in 1..9) {
                    when (cellId) {
                        1 -> buselect = bu1
                        2 -> buselect = bu2
                        3 -> buselect = bu3
                        4 -> buselect = bu4
                        5 -> buselect = bu5
                        6 -> buselect = bu6
                        7 -> buselect = bu7
                        8 -> buselect = bu8
                        9 -> buselect = bu9
                        else -> buselect = bu1

                    }

                    emcell(buselect)

                }

            } else {
                Toast.makeText(this, "You lost the game", Toast.LENGTH_LONG).show()
                bacmusic.stop()
                val looser = MediaPlayer.create(this,R.raw.losser_sound_effect)
                looser.start()
                var buselect: Button
                for (cellId in 1..9) {
                    when (cellId) {
                        1 -> buselect = bu1
                        2 -> buselect = bu2
                        3 -> buselect = bu3
                        4 -> buselect = bu4
                        5 -> buselect = bu5
                        6 -> buselect = bu6
                        7 -> buselect = bu7
                        8 -> buselect = bu8
                        9 -> buselect = bu9
                        else -> buselect = bu1

                    }

                    emcell(buselect)

                }

            }

        }


    }

    fun autoplay() {


        var buselect: Button

        var cellId: Int?

        var emptyCell = ArrayList<Int>()

        for (Id in 1..9) {
            if (!(player1.contains(Id) || player2.contains(Id))) {
                emptyCell.add(Id)
            }
        }


        var r = Random()
        val randIndex = r.nextInt(emptyCell.size - 0) + 0

        cellId = emptyCell.get(randIndex)


        //win the game
        //Row

        //row1
        if (player2.contains(1) && player2.contains(2) && !(player1.contains(3))) {
            buselect = bu3
            cellId = 3
        } else if (player2.contains(3) && player2.contains(2) && !(player1.contains(1))) {
            buselect = bu1
            cellId = 1
        } else if (player2.contains(1) && player2.contains(3) && !(player1).contains(2)) {
            buselect = bu2
            cellId = 2
        }

        //row1end


        //row2
        else if (player2.contains(4) && player2.contains(5) && !(player1.contains(6))) {
            buselect = bu6
            cellId = 6
        } else if (player2.contains(6) && player2.contains(5) && !(player1.contains(4))) {
            buselect = bu4
            cellId = 4
        } else if (player2.contains(4) && player2.contains(6) && !(player1).contains(5)) {
            buselect = bu5
            cellId = 5
        }

        //row2end


        //row3
        else if (player2.contains(7) && player2.contains(8) && !(player1.contains(9))) {
            buselect = bu9
            cellId = 9
        } else if (player2.contains(9) && player2.contains(8) && !(player1.contains(7))) {
            buselect = bu7
            cellId = 7
        } else if (player2.contains(7) && player2.contains(9) && !(player1).contains(8)) {
            buselect = bu8
            cellId = 8
        }

        //row3end
        //endRow

        //Coloum

        //col1
        else if (player2.contains(1) && player2.contains(4) && !(player1.contains(7))) {
            buselect = bu7
            cellId = 7
        } else if (player2.contains(7) && player2.contains(4) && !(player1.contains(1))) {
            buselect = bu1
            cellId = 1
        } else if (player2.contains(1) && player2.contains(7) && !(player1).contains(4)) {
            buselect = bu4
            cellId = 4
        }

        //col1end

        //col2
        else if (player2.contains(2) && player2.contains(5) && !(player1.contains(8))) {
            buselect = bu8
            cellId = 8
        } else if (player2.contains(8) && player2.contains(5) && !(player1.contains(2))) {
            buselect = bu2
            cellId = 2
        } else if (player2.contains(2) && player2.contains(8) && !(player1).contains(5)) {
            buselect = bu5
            cellId = 5
        }

        //col2end


        //col3
        else if (player2.contains(3) && player2.contains(6) && !(player1.contains(9))) {
            buselect = bu9
            cellId = 9
        } else if (player2.contains(9) && player2.contains(6) && !(player1.contains(3))) {
            buselect = bu3
            cellId = 3
        } else if (player2.contains(3) && player2.contains(9) && !(player1).contains(6)) {
            buselect = bu6
            cellId = 6
        }

        //col3end
        //endColum

        //Corner
        //leftcorner
        else if (player2.contains(1) && player2.contains(5) && !(player1).contains(9)) {
            buselect = bu9
            cellId = 9
        } else if (player2.contains(9) && player2.contains(5) && !(player1).contains(1)) {
            buselect = bu1
            cellId = 1
        } else if (player2.contains(1) && player2.contains(9) && !(player1).contains(5)) {
            buselect = bu5
            cellId = 5
        }
        //endleftCorner

        //rightCorner
        else if (player2.contains(3) && player2.contains(5) && !(player1).contains(7)) {
            buselect = bu7
            cellId = 7
        } else if (player2.contains(7) && player2.contains(5) && !(player1).contains(3)) {
            buselect = bu3
            cellId = 3
        } else if (player2.contains(3) && player2.contains(7) && !(player1).contains(5)) {
            buselect = bu5
            cellId = 5
        }
        //endrightCorner

        //endCorner


        //end win the game


        //save from lost
        //Corner
        //leftCorner
        else if (player1.contains(1) && player1.contains(5) && !(player1.contains(9)) && !(player2.contains(9))) {
            buselect = bu9
            cellId = 9


        }
        //rever
        else if (player1.contains(9) && player1.contains(5) && !(player1.contains(1)) && !(player2.contains(1))) {
            buselect = bu1
            cellId = 1


        }
        //endLeftCorner

        //RightCorner
        else if (player1.contains(3) && player1.contains(5) && !(player1.contains(7)) && !(player2.contains(7))) {
            buselect = bu7
            cellId = 7

        }
        //rev
        else if (player1.contains(7) && player1.contains(5) && !(player1.contains(3)) && !(player2.contains(3))) {
            buselect = bu3
            cellId = 3

        }
        //endRightCorner
        //endCorner

        // Line

        //line1
        else if (player1.contains(1) && player1.contains(4) && !(player1.contains(7)) && !(player2.contains(7))) {
            buselect = bu7
            cellId = 7


        }
        //revline1
        else if (player1.contains(7) && player1.contains(4) && !(player1.contains(1)) && !(player2.contains(1))) {
            buselect = bu1
            cellId = 1


        }
        //line1


        //line 2
        else if (player1.contains(2) && player1.contains(5) && !(player1.contains(8)) && !(player2.contains(8))) {
            buselect = bu8
            cellId = 8

        }
        //revline2
        else if (player1.contains(8) && player1.contains(5) && !(player1.contains(2)) && !(player2.contains(2))) {
            buselect = bu2
            cellId = 2

        }
        //line2


        //line3
        else if (player1.contains(3) && player1.contains(6) && !(player1.contains(9)) && !(player2.contains(9))) {
            buselect = bu9
            cellId = 9

        }
        //revline3
        else if (player1.contains(9) && player1.contains(6) && !(player1.contains(3)) && !(player2.contains(3))) {
            buselect = bu3
            cellId = 3

        }
        //line3


        //End Line


        //ROW

        //row1
        else if (player1.contains(1) && player1.contains(2) && !(player1.contains(3)) && !(player2.contains(3))) {
            buselect = bu3
            cellId = 3

        }

        //revrow1
        else if (player1.contains(3) && player1.contains(2) && !(player1.contains(1)) && !(player2.contains(1))) {
            buselect = bu1
            cellId = 1

        }
        //row1end


        //row2
        else if (player1.contains(4) && player1.contains(5) && !(player1.contains(6)) && !(player2.contains(6))) {
            buselect = bu6
            cellId = 6

        }
        //row2rev
        else if (player1.contains(6) && player1.contains(5) && !(player1.contains(4)) && !(player2.contains(4))) {
            buselect = bu4
            cellId = 4

        }
        //row2end

        //row3
        else if (player1.contains(7) && player1.contains(8) && !(player1.contains(9)) && !(player2.contains(9))) {
            buselect = bu9
            cellId = 9

        }
        //rowrev3
        else if (player1.contains(9) && player1.contains(8) && !(player1.contains(7)) && !(player2.contains(7))) {
            buselect = bu7
            cellId = 7

        }
        //row3end

        //row End


        //        Two corner Box but middle is blank then

        //row1

        else if (player1.contains(1) && player1.contains(3) && !(player1.contains(2)) && !(player2.contains(2))) {
            buselect = bu2
            cellId = 2
        }

        //row1end


        //row2

        else if (player1.contains(4) && player1.contains(6) && !(player1.contains(5)) && !(player2.contains(5))) {
            buselect = bu5
            cellId = 5
        }

        //row2end


        //row3

        else if (player1.contains(7) && player1.contains(9) && !(player1.contains(8)) && !(player2.contains(8))) {
            buselect = bu8
            cellId = 8
        }

        //row3end

        //Coloum

        //Col1

        else if (player1.contains(1) && player1.contains(7) && !(player1.contains(4)) && !(player2.contains(4))) {
            buselect = bu4
            cellId = 4

        }

        //end Col1

        //Col2

        else if (player1.contains(2) && player1.contains(8) && !(player1.contains(5)) && !(player2.contains(5))) {
            buselect = bu5
            cellId = 5

        }

        //end Col2

        //Col3

        else if (player1.contains(3) && player1.contains(9) && !(player1.contains(6)) && !(player2.contains(6))) {
            buselect = bu6
            cellId = 6

        }

        //end Col3

        //endColum


        //Korner

        //korner1
        else if (player1.contains(1) && player1.contains(9) && !(player1.contains(5)) && !(player2.contains(5))) {
            buselect = bu5
            cellId = 5

        }

        //endKorner1

        //korner2
        else if (player1.contains(3) && player1.contains(7) && !(player1.contains(5)) && !(player2.contains(5))) {
            buselect = bu5
            cellId = 5

        }

        //endKorner2

        //

        // end of middle is blank


        else {

            when (cellId) {

                1 -> buselect = bu1
                2 -> buselect = bu2
                3 -> buselect = bu3
                4 -> buselect = bu4
                5 -> buselect = bu5
                6 -> buselect = bu6
                7 -> buselect = bu7
                8 -> buselect = bu8
                9 -> buselect = bu9
                else -> buselect = bu1


            }
        }

        game(cellId, buselect)


    }

    fun resetbu (v: View){
        this.recreate()

    }

}
