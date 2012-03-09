package de.cinovo.q.query;


public abstract class ATableResult implements TableResult {

	/**
	 * @param col Column name
	 * @param row Row
	 * @return Value
	 */
	abstract Object getAt(final String col, final int row);

	/**
	 * @return Column names
	 */
	abstract String[] getColNames();

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		for (final String col : this.getColNames()) {
			sb.append(String.format("%20s ", col));
		}
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < this.getCols(); i++) {
			sb.append(String.format("%20s ", "--"));
		}
		sb.append(System.getProperty("line.separator"));
		for (int i = 0; i < this.getRows(); i++) {
			for (final String col : this.getColNames()) {
				final Object value = this.getAt(col, i);
				if (value == null) {
					sb.append(String.format("%20s ", ""));
				} else {
					sb.append(String.format("%20s ", value.toString()));
				}
			}
			sb.append(System.getProperty("line.separator"));
		}
		return sb.toString();
	}
}
