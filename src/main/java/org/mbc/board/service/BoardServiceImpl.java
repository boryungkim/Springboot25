package org.mbc.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mbc.board.domain.Board;
import org.mbc.board.dto.BoardDTO;
import org.mbc.board.repository.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Optional;

@Service //스프링에게 서비스 계층임을 알린다
@Log4j2
@RequiredArgsConstructor
@Transactional //commit용 (여러개의 테이블이 조합될 때 해결 역할)

public class BoardServiceImpl implements BoardService {

    private final ModelMapper modelMapper; // 엔티티와 dto를 변환
    private final BoardRepository boardRepository; // jpa용 클래스 (crud, 페이징, 정렬, 다중 검색)

    @Override
    public Long register(BoardDTO boardDTO) { // 조원이 실행 코드를 만든다

        Board board =  modelMapper.map(boardDTO, Board.class); //엔티티가 dto로 변환

        Long bno = boardRepository.save(board).getBno();
        //                             INSERT INTO BOARD ~~~ => BNO 를 받는다

        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno) {
        Optional <Board> result = boardRepository.findById(bno);
        //select * from board where bno = bno
        //Optional null이 나와도 예외처리 하지 않음

        Board board = result.orElseThrow(); //정상값이 나오면 엔티티로 받는다

        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        //모델 매퍼를 이용해서 엔티티로 나온 board를 dto로 변환한다

        return boardDTO;

    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        //select * from board where bno = bno -> 엔티티로 나옴

        Board board = result.orElseThrow(); //성공시(null이 아닐 때) 결과를 엔티티로 저장
        board.change(boardDTO.getTitle(), boardDTO.getContent()); //제목과 내용이 수 정
        boardRepository.save(board); //데이터베이스에 pk가 있으면 update, 없으면 insert
    }

    @Override
    public void remove(Long bno) {
        boardRepository.deleteById(bno);
        //delete from board where bno = bno
    }
}
