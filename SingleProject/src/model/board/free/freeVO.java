package model.board.free;

public class freeVO {
	String idx, name, subject, contents, readcnt, regdate, board_div, id_no;
	String reply, refer_id, parent_no, parent_re;
	int reply_cnt, depth;

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public String getParent_re() {
		return parent_re;
	}

	public void setParent_re(String parent_re) {
		this.parent_re = parent_re;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public int getReply_cnt() {
		return reply_cnt;
	}

	public void setReply_cnt(int reply_cnt) {
		this.reply_cnt = reply_cnt;
	}

	public String getParent_no() {
		return parent_no;
	}

	public void setParent_no(String parent_no) {
		this.parent_no = parent_no;
	}

	public String getIdx() {
		return idx;
	}

	public void setIdx(String idx) {
		this.idx = idx;
	}

	public String getRefer_id() {
		return refer_id;
	}

	public void setRefer_id(String refer_id) {
		this.refer_id = refer_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(String readcnt) {
		this.readcnt = readcnt;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getBoard_div() {
		return board_div;
	}

	public void setBoard_div(String board_div) {
		this.board_div = board_div;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}
}
