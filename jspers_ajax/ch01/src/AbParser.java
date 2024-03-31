import java.math.BigDecimal;
import java.util.Locale;
/*
 * AbParser를 사용할 때 주의 사항
 * parse() 메서드는 직접 호출하도록 의도하고 구현되지 않았습니다.
 * doParse() 메서드를 사용하면 파싱이 시작됩니다.
 */
public abstract class AbParser implements IParser {
	public abstract void parse();

	public final String getParserName() {
		return this.getClass().getName();
	}

	private String getNextStreamToken() {
		return null;
	}

	public final void doParse() {
		parse();
		generateAbstractSyntaxTree();
	}

	protected void generateAbstractSyntaxTree() {
	}
}

// 토끼야 parse() 메서드만 구현해라.
class 한글파서 extends AbParser {
	public final void parse() {}
}

// 거북아 parse() 메서드만 구현해라.
abstract class AbEnglishParser extends AbParser {
	public final void parse() {}
	public abstract BigDecimal getByteLength();

	public Locale getLocale() {
		return Locale.getDefault();
	}
}

class NorthAmericaEnglishParser extends AbEnglishParser {
	public BigDecimal getByteLength() {	return null;	}
}
